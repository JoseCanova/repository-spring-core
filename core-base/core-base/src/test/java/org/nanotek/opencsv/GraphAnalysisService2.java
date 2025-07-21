package org.nanotek.opencsv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.nanotek.BaseEntity;
import org.nanotek.graph.brainz.MusicBrainzKnowledgeGraph;
import org.nanotek.opencsv.metrics.VertexWeigth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GraphAnalysisService2 {
	
	@Autowired
	MusicBrainzKnowledgeGraph brainzGraphModel;

private static final int LONG_BYTES = 8;
private static final int UUID_BYTES = 16;
private static final int STRING_DEFAULT_BYTES = 255;
private static final int INTEGER_BYTES = 4;
private static final int BOOLEAN_BYTES = 1;
private static final int FOREIGN_KEY_BYTES = 8;
private static final int NEWLINE_BYTES = 1;

private Map<Class<?>, Long> vertexWeights;
private Map<Class<?>, Long> movementWeights;
private List<Class<?>> sortedClasses; // Topological order

/**
 * Calculates the "imaginary weight" (w(X)vertex) of a class, including inherited fields.
 * This weight is an abstract measure of the data size for a single instance of the entity.
 * @param clazz The class for which to calculate the vertex weight.
 * @return The calculated imaginary vertex weight in bytes.
 */
public static long getImaginaryVertexWeight(Class<?> clazz) {
	long totalWeight = 0;
	Set<Field> processedFields = new HashSet<>();

	Class<?> currentClass = clazz;
	while (currentClass != null && BaseEntity.class.isAssignableFrom(currentClass)) {
	for (Field field : currentClass.getDeclaredFields()) {
		if (processedFields.add(field)) {
		Class<?> fieldType = field.getType();
		long fieldWeight = 0;

		if (fieldType == Long.class || fieldType == long.class) {
			fieldWeight = LONG_BYTES;
		} else if (fieldType == UUID.class) {
			fieldWeight = UUID_BYTES;
		} else if (fieldType == String.class) {
			Column columnAnnotation = field.getAnnotation(Column.class);
			if (columnAnnotation != null) {
			if (columnAnnotation.length() > 0) {
				fieldWeight = columnAnnotation.length();
			} else if (columnAnnotation.columnDefinition() != null && !columnAnnotation.columnDefinition().isEmpty()) {
				String def = columnAnnotation.columnDefinition().toUpperCase();
				if (def.contains("VARCHAR(") && def.contains(")")) {
				try {
					int start = def.indexOf("VARCHAR(") + "VARCHAR(".length();
					int end = def.indexOf(")", start);
					fieldWeight = Long.parseLong(def.substring(start, end));
				} catch (Exception e) {
					fieldWeight = STRING_DEFAULT_BYTES;
				}
				} else {
				fieldWeight = STRING_DEFAULT_BYTES;
				}
			} else {
				fieldWeight = STRING_DEFAULT_BYTES;
			}
			} else {
			fieldWeight = STRING_DEFAULT_BYTES;
			}
		} else if (fieldType == Integer.class || fieldType == int.class) {
			fieldWeight = INTEGER_BYTES;
		} else if (fieldType == Boolean.class || fieldType == boolean.class) {
			fieldWeight = BOOLEAN_BYTES;
		} else if (Collection.class.isAssignableFrom(fieldType) || fieldType.isArray()) {
			fieldWeight = 0; // Collections and arrays are not directly contributing to vertex weight in this context
		} else if (BaseEntity.class.isAssignableFrom(fieldType)) {
			fieldWeight = FOREIGN_KEY_BYTES; // Foreign keys
		} else {
			// System.out.println("Warning: Unhandled field type '" + fieldType.getSimpleName() + "' for field '" + field.getName() + "' in class '" + clazz.getSimpleName() + "'. Assuming 0 bytes for vertex weight.");
			fieldWeight = 0;
		}
		totalWeight += fieldWeight;
		}
	}
	currentClass = currentClass.getSuperclass();
	}
	System.err.println("Total Weight " + clazz.getSimpleName() + ": " + totalWeight);
	return totalWeight + NEWLINE_BYTES; // Account for an abstract newline character or record separator
}

/**
 * Initializes the graph analysis service by calculating vertex weights,
 * building a dependency graph, performing a topological sort, and
 * calculating movement weights for all entities in the MusicBrainz knowledge graph.
 * This method runs automatically after the bean is constructed.
 */
@PostConstruct // This method runs after the bean is constructed and dependencies are injected
public void init() {
	// Ensure the brainzGraphModel is not null before proceeding
	if (brainzGraphModel == null || brainzGraphModel.getDirectedGraph() == null) {
	System.err.println("Warning: MusicBrainzKnowledgeGraph or its directed graph is not initialized. GraphAnalysisService cannot proceed.");
	vertexWeights = new HashMap<>();
	movementWeights = new HashMap<>();
	sortedClasses = new ArrayList<>();
	return;
	}

	Set<Class<? extends BaseEntity>> allClasses = brainzGraphModel.getDirectedGraph().vertexSet();
	if (allClasses.isEmpty()) {
	System.err.println("Warning: No entities found in MusicBrainzKnowledgeGraph. GraphAnalysisService cannot proceed.");
	vertexWeights = new HashMap<>();
	movementWeights = new HashMap<>();
	sortedClasses = new ArrayList<>();
	return;
	}


	// 2. Calculate w(X)vertex for all classes
	vertexWeights = new HashMap<>();
	for (Class<?> clazz : allClasses) {
	vertexWeights.put(clazz, getImaginaryVertexWeight(clazz));
	}

	// 3. Build Dependency Graph and Precedence Rules (using in-degrees for topological sort)
	Map<Class<?>, Set<Class<?>>> dependencies = new HashMap<>(); // Key: Parent Class, Value: Set of Classes that depend on Key
	Map<Class<?>, Integer> inDegrees = new HashMap<>(); // Key: Class, Value: Number of its incoming dependencies

	for (Class<?> clazz : allClasses) {
	dependencies.put(clazz, new HashSet<>());
	inDegrees.put(clazz, 0);
	}

	for (Class<?> sourceClass : allClasses) {
	Set<Class<?>> uniqueParentsForSourceClass = new HashSet<>(); // To handle multiple fields referencing the same parent
	Class<?> currentReflectedClass = sourceClass;
	while (currentReflectedClass != null && BaseEntity.class.isAssignableFrom(currentReflectedClass)) { // Iterate through inheritance hierarchy
		for (Field field : currentReflectedClass.getDeclaredFields()) {
		Class<?> targetClass = field.getType();

		boolean isManyToOne = field.isAnnotationPresent(ManyToOne.class);
		boolean isOneToOne = field.isAnnotationPresent(OneToOne.class);
		// If the field is a foreign key relationship (ManyToOne or OneToOne)
		// and it points to another BaseEntity within our set of all entities
		if ((isManyToOne || isOneToOne) && BaseEntity.class.isAssignableFrom(targetClass) && allClasses.contains(targetClass)) {
			dependencies.get(targetClass).add(sourceClass); // targetClass is a parent of sourceClass
			if (uniqueParentsForSourceClass.add(targetClass)) {// Ensure we only increment in-degree once per unique parent
			inDegrees.merge(sourceClass, 1, Integer::sum);// sourceClass depends on targetClass
			}
		}
		}
		currentReflectedClass = currentReflectedClass.getSuperclass();
	}
	}

	Map<Class<?>, Integer> initialInDegrees = new HashMap<>(inDegrees); // Preserve initial in-degrees for sorting comparison

	// 4. Topological Sort (Kahn's algorithm)
	sortedClasses = new ArrayList<>();
	PriorityQueue<Class<?>> pqForSort = new PriorityQueue<>(
	(c1, c2) -> {
		// Primary sort by in-degree (lower in-degree first)
		int inDegreeComparison = initialInDegrees.get(c1).compareTo(initialInDegrees.get(c2));
		if (inDegreeComparison != 0) {
		return inDegreeComparison;
		}
		// Secondary sort by simple name for stable ordering among equal in-degrees
		return c1.getSimpleName().compareTo(c2.getSimpleName());
	}
	);

	Map<Class<?>, Integer> currentInDegrees = new HashMap<>(initialInDegrees); // Mutable in-degrees for the algorithm
	for (Class<?> clazz : allClasses) {
	if (currentInDegrees.get(clazz) == 0) {
		pqForSort.offer(clazz); // Add all nodes with 0 in-degree to the queue
	}
	}

	while (!pqForSort.isEmpty()) {
	Class<?> current = pqForSort.poll();
	sortedClasses.add(current); // Add to sorted list

	// For each neighbor (dependent) of the current node
	// Note: dependencies map is Parent -> {Dependents}, so current is a Parent
	Set<Class<?>> dependents = dependencies.get(current);
	if (dependents != null) {
		for (Class<?> dependent : dependents) {
		currentInDegrees.merge(dependent, -1, Integer::sum); // Decrement in-degree
		if (currentInDegrees.get(dependent) == 0) {
			pqForSort.offer(dependent); // If in-degree becomes 0, add to queue
		}
		}
	}
	}

	if (sortedClasses.size() != allClasses.size()) {
	System.err.println("Error: Cyclic dependency detected in GraphAnalysisService initialization! Topological sort could not include all classes.");
	// Handle cyclic dependency: potentially log the cycle, or decide on a fallback strategy
	movementWeights = new HashMap<>(); // Initialize empty if cycle
	// You might want to throw an exception here or provide a mechanism to analyze the cycle
	return;
	}

	// 5. Calculate w(X)mov (movement weight) using the recursive formula in reverse topological order
	movementWeights = new HashMap<>();
	List<Class<?>> reversedSortedClasses = new ArrayList<>(sortedClasses);
	Collections.reverse(reversedSortedClasses); // Process in reverse topological order

	System.out.println("Topological Sorting Order:");
	for (Class<?> clazz : sortedClasses) {
	System.err.println("SORTED_CLASS :" + clazz.getSimpleName()); // Changed to capital for consistency
	}

	for (Class<?> targetClass : reversedSortedClasses) {
	long initialWeight = vertexWeights.getOrDefault(targetClass, 0L); // Use getOrDefault for safety
	long totalDependentContribution = 0;

	// Find classes that 'depend' on targetClass (i.e., targetClass is a parent to them)
	Set<Class<?>> directDependentsOfTargetClass = dependencies.get(targetClass);
	if (directDependentsOfTargetClass != null) {
		for (Class<?> Yi : directDependentsOfTargetClass) {
		// Contribution includes the vertex weight of the dependent and its movement weight
		totalDependentContribution += vertexWeights.getOrDefault(Yi, 0L);
		totalDependentContribution += movementWeights.getOrDefault(Yi, 0L); // Already computed due to reverse topological order
		}
	}
	movementWeights.put(targetClass, initialWeight + totalDependentContribution);
	}
}

    /**
     * Returns an unmodifiable map of vertex weights, representing the calculated
     * "imaginary weight" (size) for each entity class.
     * @return A map where keys are entity classes and values are their vertex weights.
     */
public Map<Class<?>, Long> getVertexWeights() {
	return Collections.unmodifiableMap(vertexWeights);
}

    /**
     * Returns an unmodifiable map of movement weights, representing the calculated
     * cumulative weight for each entity class, considering its own vertex weight
     * and the combined vertex and movement weights of all entities that depend on it.
     * @return A map where keys are entity classes and values are their movement weights.
     */
public Map<Class<?>, Long> getMovementWeights() {
	return Collections.unmodifiableMap(movementWeights);
}

    /**
     * Returns an unmodifiable list of classes sorted in topological order.
     * This order respects dependencies (i.e., a class appears before classes that depend on it).
     * @return A list of classes in topological order.
     */
public List<Class<?>> getSortedClasses() {
	return Collections.unmodifiableList(sortedClasses);
}
    
    /**
     * Generates and returns a priority queue of entity classes, ordered for processing.
     * The priority is determined primarily by the calculated 'movement weight'
     * (lower movement weight means higher priority, implying less dependencies or "base-like" entities).
     * If movement weights are equal, classes are ordered alphabetically by their simple name
     * for stable and deterministic processing order.
     *
     * @return A {@code PriorityQueue} of {@code Class<?>} representing the prioritized
     * order for processing entities based on their calculated weights.
     * @throws IllegalStateException if the GraphAnalysisService has not been initialized
     * (e.g., {@code @PostConstruct} method not yet run or
     * cyclic dependency detected during initialization), preventing movement weights from being calculated.
     */
    public PriorityQueue<VertexWeigth> getProcessingPriorityQueue() {
        // Ensure that movementWeights have been calculated by the @PostConstruct method
        if (movementWeights == null || movementWeights.isEmpty()) {
            throw new IllegalStateException("GraphAnalysisService not fully initialized or cyclic dependency detected during initialization. Cannot generate processing priority queue.");
        }

        // Create a new PriorityQueue with a custom comparator
        PriorityQueue<VertexWeigth> processingQueue = new PriorityQueue<>();

        // Add all classes for which movement weights have been calculated to the priority queue
        Set<VertexWeigth> theSet = movementWeights
    			.entrySet()
    			.stream()
    			.map(e -> new VertexWeigth(e.getKey(),e.getValue()))
    			.collect(Collectors.toSet());
        processingQueue.addAll(theSet);
        
        return processingQueue;
    }
}
