package org.nanotek.opencsv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistType;
import org.nanotek.beans.entity.Gender;
import org.nanotek.entities.metamodel.BrainzGraphModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GraphAnalysisService {
	
	@Autowired
	BrainzGraphModel brainzGraphModel;

    // --- Abstract Byte Size Definitions (Copied from previous context) ---
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
                        fieldWeight = 0;
                    } else if (BaseEntity.class.isAssignableFrom(fieldType)) {
                        fieldWeight = FOREIGN_KEY_BYTES;
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
        return totalWeight + NEWLINE_BYTES;
    }

    @PostConstruct // This method runs after the bean is constructed and dependencies are injected
    public void init() {
        Set<Class<? extends BaseEntity>> allClasses = brainzGraphModel.getDirectedGraph().vertexSet();

        // 2. Calculate w(X)vertex for all classes
        vertexWeights = new HashMap<>();
        for (Class<?> clazz : allClasses) {
            vertexWeights.put(clazz, getImaginaryVertexWeight(clazz));
        }

        // 3. Build Dependency Graph and Precedence Rules
        Map<Class<?>, Set<Class<?>>> dependencies = new HashMap<>(); // P -> {C1, C2...} where C depends on P
        Map<Class<?>, Integer> inDegrees = new HashMap<>(); // Number of incoming edges to C (how many P's C depends on)
        
        for (Class<?> clazz : allClasses) {
            dependencies.put(clazz, new HashSet<>());
            inDegrees.put(clazz, 0);
        }

        for (Class<?> sourceClass : allClasses) {
            Set<Class<?>> uniqueParentsForSourceClass = new HashSet<>();
            for (Field field : sourceClass.getDeclaredFields()) {
                Class<?> targetClass = field.getType();

                boolean isManyToOne = field.isAnnotationPresent(ManyToOne.class);
                boolean isOneToOne = field.isAnnotationPresent(OneToOne.class); 
                
                if ((isManyToOne || isOneToOne) && BaseEntity.class.isAssignableFrom(targetClass) && allClasses.contains(targetClass)) {
                    dependencies.get(targetClass).add(sourceClass);
                    if (uniqueParentsForSourceClass.add(targetClass)) { 
                        inDegrees.merge(sourceClass, 1, Integer::sum); 
                    }
                }
            }
        }
        
        Map<Class<?>, Integer> initialInDegrees = new HashMap<>(inDegrees);

        // 4. Topological Sort
        sortedClasses = new ArrayList<>();
        PriorityQueue<Class<?>> pqForSort = new PriorityQueue<>(
            (c1, c2) -> {
                int inDegreeComparison = initialInDegrees.get(c1).compareTo(initialInDegrees.get(c2));
                if (inDegreeComparison != 0) {
                    return inDegreeComparison;
                }
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            }
        );

        Map<Class<?>, Integer> currentInDegrees = new HashMap<>(initialInDegrees);
        for (Class<?> clazz : allClasses) {
            if (currentInDegrees.get(clazz) == 0) {
                pqForSort.offer(clazz);
            }
        }

        while (!pqForSort.isEmpty()) {
            Class<?> current = pqForSort.poll();
            sortedClasses.add(current);

            for (Class<?> dependent : dependencies.get(current)) { 
                currentInDegrees.merge(dependent, -1, Integer::sum);
                if (currentInDegrees.get(dependent) == 0) {
                    pqForSort.offer(dependent);
                }
            }
        }

        if (sortedClasses.size() != allClasses.size()) {
            System.err.println("Error: Cyclic dependency detected in GraphAnalysisService initialization!");
            movementWeights = new HashMap<>(); // Initialize empty if cycle
            return;
        }

        // 5. Calculate w(X)mov using the new recursive formula in reverse topological order
        movementWeights = new HashMap<>();
        List<Class<?>> reversedSortedClasses = new ArrayList<>(sortedClasses);
        Collections.reverse(reversedSortedClasses);

        System.out.println("Topological Sorting Order:");
        for (Class<?> clazz : sortedClasses) {
            System.err.println("SORTERCLASS :" + clazz.getSimpleName());
        }

        
        for (Class<?> targetClass : reversedSortedClasses) {
            long initialWeight = vertexWeights.get(targetClass);
            long totalDependentContribution = 0;

            Set<Class<?>> directDependentsOfX = dependencies.get(targetClass);
            if (directDependentsOfX != null) {
                for (Class<?> Yi : directDependentsOfX) {
                    totalDependentContribution += vertexWeights.get(Yi);
                    totalDependentContribution += movementWeights.get(Yi); // Already computed due to reverse order
                }
            }
            movementWeights.put(targetClass, initialWeight + totalDependentContribution);
        }
    }

    public Map<Class<?>, Long> getVertexWeights() {
        return Collections.unmodifiableMap(vertexWeights);
    }

    public Map<Class<?>, Long> getMovementWeights() {
        return Collections.unmodifiableMap(movementWeights);
    }

    public List<Class<?>> getSortedClasses() {
        return Collections.unmodifiableList(sortedClasses);
    }
}