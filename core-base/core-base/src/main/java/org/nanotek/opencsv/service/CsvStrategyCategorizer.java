package org.nanotek.opencsv.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity; // New import for BaseEntity
import org.nanotek.collections.BaseMap;
import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.graph.brainz.MusicBrainzKnowledgeGraph;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to categorize CSV file strategies into 'basetype' and 'regular' categories
 * based on the structural analysis of their associated JPA entity classes.
 * Basetype classes are identified as those that primarily serve as lookup tables,
 * characterized by the absence of ManyToOne or OneToOne relationships, OR
 * if such relationships exist, they point to entities NOT part of the main
 * BrainzGraphModel's direct dependency graph.
 */
@Service
public class CsvStrategyCategorizer<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>> {

    private final CsvFileConfigurations<T,S,P,M> csvFileConfigurations;

    @Autowired
    private MusicBrainzKnowledgeGraph brainzGraphModel; // Injected for graph model access

    public CsvStrategyCategorizer(@Autowired CsvFileConfigurations<T,S,P,M> csvFileConfigurations) {
        this.csvFileConfigurations = csvFileConfigurations;
    }

    /**
     * Categorizes all configured CSV strategies into basetype and regular groups
     * by analyzing the JPA relationship annotations on their associated entity classes.
     *
     * @return A CategorizedCsvStrategies object containing two maps:
     * one for basetype strategies and one for regular strategies.
     */
    public CategorizedCsvStrategies<T,S,P,M> categorizeStrategies() {
        System.out.println("\n--- CsvStrategyCategorizer: Performing Strategy Categorization by Entity Structure ---");

        Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> allStrategies =
            csvFileConfigurations.getCsvConfigs();

        if (allStrategies.isEmpty()) {
            System.out.println("No CSV configurations found to categorize.");
            return new CategorizedCsvStrategies<>(Collections.emptyMap(), Collections.emptyMap());
        }

        // Get the vertex set from the BrainzGraphModel's directed graph once for efficiency
        Set<Class<? extends BaseEntity>> graphVertices = Collections.emptySet();
        if (brainzGraphModel != null && brainzGraphModel.getDirectedGraph() != null) { // Changed to getDirectedGraph()
            graphVertices = brainzGraphModel.getDirectedGraph().vertexSet();
            System.out.println("DEBUG: BrainzGraphModel directed graph has " + graphVertices.size() + " vertices.");
        } else {
            System.err.println("WARNING: BrainzGraphModel or its directed graph is null. Structural analysis might be incomplete.");
        }
        final Set<Class<? extends BaseEntity>> finalGraphVertices = graphVertices; // For use in lambda

        Map<Boolean, Map<String, CsvFileItemConcreteStrategy<T,S,P,M>>> partitioned =
            allStrategies.entrySet().stream()
                .collect(Collectors.partitioningBy(entry -> {
                    CsvFileItemConcreteStrategy<T,S,P,M> strategy = entry.getValue();
                    Class<? extends BaseEntity> entityClass = null; // More specific type
                    try {
                        // 1. Get the Class<?> representing the immutable bean type
                        Class<?> immutableBeanClass = (Class<?>) strategy.getImmutable();

                        // 2. Instantiate the immutable bean class
                        // This assumes a no-arg constructor is available.
                        Object instance = immutableBeanClass.getDeclaredConstructor().newInstance();

                        // 3. Cast the instance to BaseBean
                        BaseBean<?, ?> immutableBeanInstance = BaseBean.class.cast(instance);

                        // 4. Get the actual entity class from the instance and cast to BaseEntity subclass
                        entityClass = (Class<? extends BaseEntity>) immutableBeanInstance.getBaseClass();

                    } catch (ClassCastException e) {
                        System.err.println("Error casting immutable to Class<?> or BaseEntity for strategy " + entry.getKey() + ": " + e.getMessage());
                        return false; // Not the expected type, so not a basetype
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        System.err.println("Error instantiating immutable bean for strategy " + entry.getKey() + ": " + e.getMessage());
                        e.printStackTrace(); // Print stack trace for reflection errors
                        return false; // Cannot instantiate, so cannot determine structure
                    } catch (Exception e) {
                        System.err.println("Unexpected error getting entity class for strategy " + entry.getKey() + ": " + e.getMessage());
                        e.printStackTrace();
                        return false;
                    }

                    // Pass the graph vertices to the structural analysis method
                    boolean isBasetype = (entityClass != null) && isBasetypeByStructure(entityClass, finalGraphVertices);
                    System.out.println(String.format("  - Strategy '%s' (Entity: %s) isBasetype: %b (Structural Check)",
                                                    entry.getKey(),
                                                    entityClass != null ? entityClass.getSimpleName() : "N/A",
                                                    isBasetype));
                    return isBasetype;
                }, Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> basetype =
            Collections.unmodifiableMap(partitioned.getOrDefault(true, new HashMap<>()));
        Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> regular =
            Collections.unmodifiableMap(partitioned.getOrDefault(false, new HashMap<>()));

        System.out.println(String.format("--- CsvStrategyCategorizer: Categorization Complete. Basetypes: %d, Regular: %d ---",
                                         basetype.size(), regular.size()));

        return new CategorizedCsvStrategies<>(basetype, regular);
    }

    /**
     * Determines if an entity class is a "basetype" based on its JPA relationship annotations
     * and whether those relationships point to entities within the main dependency graph.
     * A basetype is defined as a class that does NOT have @ManyToOne or @OneToOne relationships
     * that point to a vertex in the provided graphVertices set.
     * It MAY have @OneToMany relationships.
     *
     * @param entityClass The Class object of the JPA entity, expected to extend BaseEntity.
     * @param graphVertices The set of Class<? extends BaseEntity> objects representing vertices in the main dependency graph.
     * @return true if the class is considered a basetype by structure, false otherwise.
     */
    private boolean isBasetypeByStructure(Class<? extends BaseEntity> entityClass, Set<Class<? extends BaseEntity>> graphVertices) {
        if (entityClass == null) {
            return false;
        }

        Class<?> currentClass = entityClass;
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(ManyToOne.class) ||
                    field.isAnnotationPresent(OneToOne.class)) {

                    Class<?> targetFieldType = field.getType(); // Get the type of the field

                    // Check if the targetFieldType is assignable from BaseEntity,
                    // and if it's contained in the graphVertices set.
                    // This ensures we only consider relationships to other *entities* in our graph.
                    if (BaseEntity.class.isAssignableFrom(targetFieldType) && graphVertices.contains(targetFieldType)) {
                        System.out.println(String.format("    - Entity %s has a %s relationship field '%s' pointing to graph vertex %s. NOT a basetype.",
                                                         entityClass.getSimpleName(),
                                                         field.isAnnotationPresent(ManyToOne.class) ? "@ManyToOne" : "@OneToOne",
                                                         field.getName(),
                                                         targetFieldType.getSimpleName()));
                        return false;
                    } else {
                        System.out.println(String.format("    - Entity %s has a %s relationship field '%s' pointing to non-graph entity/ignored type %s. (Ignored for basetype check)",
                                                         entityClass.getSimpleName(),
                                                         field.isAnnotationPresent(ManyToOne.class) ? "@ManyToOne" : "@OneToOne",
                                                         field.getName(),
                                                         targetFieldType.getSimpleName()));
                    }
                }
            }
            currentClass = currentClass.getSuperclass(); // Move up the inheritance hierarchy
        }
        return true; // No relevant ManyToOne or OneToOne relationships found
    }

    // Getter for BrainzGraphModel
    public MusicBrainzKnowledgeGraph getBrainzGraphModel() {
        return brainzGraphModel;
    }
}