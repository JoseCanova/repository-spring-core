import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.*;

// --- Custom Annotations (Simulating JPA annotations for prototype) ---
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyManyToOne {} // Custom ManyToOne

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyOneToOne {} // Custom OneToOne

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyNotNull {} // Custom NotNull

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyJoinTable {
    String name();
    MyJoinColumn[] joinColumns() default {};
    MyJoinColumn[] inverseJoinColumns() default {};
} // Custom JoinTable

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyJoinColumn {
    String name();
    String referencedColumnName();
} // Custom JoinColumn

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyColumn {
    String name() default "";
    boolean nullable() default true;
    String columnDefinition() default "";
    int length() default 0; // Explicit length for VARCHAR(n)
} // Custom Column

interface BaseEntity{}

// --- Simplified Placeholder Classes (Reflect actual fields and relevant annotations) ---
// These mimic the structure of your actual classes for the algorithm demonstration.

class BaseType implements BaseEntity {
    public Long typeId;
    public UUID gid;
    @MyColumn(name="name", columnDefinition = "VARCHAR NOT NULL")
    public String name;
}

class AreaType extends BaseType {}
class ArtistType extends BaseType {}
class Gender extends BaseType {}

class Area implements BaseEntity {
    public Long areaId;
    public UUID gid;
    
    @MyManyToOne // Assuming an FK to AreaType
    public AreaType areaType; 

    // Assuming other FKs are Objects for simplicity, will be treated as FK_BYTES
    public Object areaBeginDate; 
    public Object areaEndDate;
    public Object areaComment;
    
    @MyColumn(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
    public String areaName;
}

class Artist implements BaseEntity {
    public Long artistId;
    public UUID gid;

    @MyColumn(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
    public String artistName;

    // Assuming FKs
    public Object artistSortName; 
    public Object artistComment; 
    public Object artistBeginDate; 
    public Object artistEndDate; 

    @MyManyToOne
    public ArtistType artistType; 
    
    @MyNotNull
    @MyManyToOne // Gender relationship, as per your example
    @MyJoinTable(
            name = "artist_gender_join",
            joinColumns = @MyJoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @MyJoinColumn(name = "gender_id", referencedColumnName = "id")
    )
    public Gender gender; 
    
    @MyManyToOne
    public Area area; 
    
    @MyManyToOne
    public Area beginArea; 
    
    @MyManyToOne
    public Area endArea; 

    // List fields are explicitly ignored for row size calculation
    // private List<ArtistCredit> artistCredits;
    // private Set<ArtistAlias> artistAlias;
}


public class GraphOrderingAlgorithm {

    // --- Abstract Byte Size Definitions ---
    private static final int LONG_BYTES = 8;
    private static final int UUID_BYTES = 16;
    private static final int STRING_DEFAULT_BYTES = 255; // For @Column without explicit length, or unannotated Strings
    private static final int INTEGER_BYTES = 4;
    private static final int BOOLEAN_BYTES = 1;
    private static final int FOREIGN_KEY_BYTES = 8; // Assuming FKs are stored as LONG IDs
    private static final int NEWLINE_BYTES = 1; // Per instance

    // --- Precedence Types ---
    enum PrecedenceType {
        ABSOLUTE, // Must precede (e.g., @NotNull @MyManyToOne)
        WEAK,     // Should precede, but can be tied by weight (e.g., nullable @MyManyToOne)
        NONE      // No direct precedence rule from this relationship
    }

    /**
     * Calculates the "imaginary weight" (w(X)vertex) of a class, including inherited fields.
     */
    public static long getImaginaryVertexWeight(Class<?> clazz) {
        long totalWeight = 0;
        Set<Field> processedFields = new HashSet<>();

        // Process fields from the current class and all its superclasses up to BaseEntity
        Class<?> currentClass = clazz;
        while (currentClass != null && BaseEntity.class.isAssignableFrom(currentClass)) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (processedFields.add(field)) { // Only process each unique field once
                    Class<?> fieldType = field.getType();
                    long fieldWeight = 0;

                    if (fieldType == Long.class || fieldType == long.class) {
                        fieldWeight = LONG_BYTES;
                    } else if (fieldType == UUID.class) {
                        fieldWeight = UUID_BYTES;
                    } else if (fieldType == String.class) {
                        MyColumn columnAnnotation = field.getAnnotation(MyColumn.class);
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
                        System.out.println("Warning: Unhandled field type '" + fieldType.getSimpleName() + "' for field '" + field.getName() + "' in class '" + clazz.getSimpleName() + "'. Assuming 0 bytes for vertex weight.");
                        fieldWeight = 0;
                    }
                    totalWeight += fieldWeight;
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return totalWeight + NEWLINE_BYTES;
    }

    public static void main(String[] args) {
        // --- 1. Define and Register Classes ---
        Set<Class<?>> allClasses = new HashSet<>(Arrays.asList(Area.class, Artist.class, AreaType.class, ArtistType.class, Gender.class));

        // --- 2. Calculate w(X)vertex for all classes ---
        Map<Class<?>, Long> vertexWeights = new HashMap<>();
        for (Class<?> clazz : allClasses) {
            vertexWeights.put(clazz, getImaginaryVertexWeight(clazz));
        }

        // --- 3. Build Dependency Graph and Precedence Rules ---
        // (P -> C means P precedes C, or C depends on P)
        Map<Class<?>, Set<Class<?>>> dependencies = new HashMap<>(); // P -> {C1, C2...} where C depends on P
        Map<Class<?>, Integer> inDegrees = new HashMap<>(); // Number of incoming edges to C (how many P's C depends on)
        
        for (Class<?> clazz : allClasses) {
            dependencies.put(clazz, new HashSet<>());
            inDegrees.put(clazz, 0); // Initialize in-degrees
        }

        for (Class<?> sourceClass : allClasses) { // SourceClass (C) is the class that has the FK field
            Set<Class<?>> uniqueParentsForSourceClass = new HashSet<>(); // Keep track of unique parents for this sourceClass
            for (Field field : sourceClass.getDeclaredFields()) {
                Class<?> targetClass = field.getType(); // TargetClass (P) is the class being referenced (the parent)

                boolean isManyToOne = field.isAnnotationPresent(MyManyToOne.class);
                boolean isOneToOne = field.isAnnotationPresent(MyOneToOne.class); 
                
                // Ensure the target class is one of our defined entities
                if ((isManyToOne || isOneToOne) && BaseEntity.class.isAssignableFrom(targetClass) && allClasses.contains(targetClass)) {
                    // This indicates an edge from P (targetClass) to C (sourceClass) in the precedence graph
                    // meaning P must precede C.
                    dependencies.get(targetClass).add(sourceClass); // Add C to P's list of dependents
                    // ONLY increment in-degree once per unique parent
                    if (uniqueParentsForSourceClass.add(targetClass)) { 
                        inDegrees.merge(sourceClass, 1, Integer::sum); 
                    }
                }
            }
        }
        
        // --- Store initial in-degrees before topological sort modifies them ---
        Map<Class<?>, Integer> initialInDegrees = new HashMap<>();
        for (Map.Entry<Class<?>, Integer> entry : inDegrees.entrySet()) {
            initialInDegrees.put(entry.getKey(), entry.getValue());
        }

        // --- Debugging: Print initial graph state ---
        System.out.println("\n--- Initial In-Degrees ---");
        initialInDegrees.forEach((clazz, degree) -> System.out.println(clazz.getSimpleName() + ": " + degree));
        System.out.println("\n--- Dependencies (Parent -> {Direct Dependents}) ---");
        dependencies.forEach((parent, dependents) -> {
            System.out.print(parent.getSimpleName() + " -> {");
            if (dependents != null) {
                dependents.forEach(d -> System.out.print(d.getSimpleName() + ", "));
            }
            System.out.println("}");
        });
   
        // --- 4. Topological Sort ---
        List<Class<?>> sortedClasses = new ArrayList<>();
        PriorityQueue<Class<?>> pqForSort = new PriorityQueue<>(
            (c1, c2) -> {
                // For topological sort, prioritize by in-degree first
                int inDegreeComparison = initialInDegrees.get(c1).compareTo(initialInDegrees.get(c2)); // Use initialInDegrees for consistent sorting
                if (inDegreeComparison != 0) {
                    return inDegreeComparison;
                }
                // Break ties by class name for deterministic order if needed, or by vertex weight as a fallback
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            }
        );

        // Populate PQ for sort using a copy of inDegrees as it will be modified
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

        // --- Check for cycles immediately after topological sort ---
        if (sortedClasses.size() != allClasses.size()) {
            System.out.println("\nWarning: Cyclic dependency detected! Not all classes could be sorted topologically.");
            System.out.println("Classes not sorted (part of a cycle or unreachable):");
            for (Class<?> clazz : allClasses) {
                if (!sortedClasses.contains(clazz)) {
                    System.out.println("- " + clazz.getSimpleName() + " (Final In-Degree: " + currentInDegrees.get(clazz) + ")");
                }
            }
            // Exit or skip further calculations if a cycle is detected
            System.out.println("\nCannot calculate movement weights or a complete order due to cyclic dependency.");
            
            System.out.println("\n--- Detailed Weights (Partial) ---");
            System.out.println("Vertex Weights (w(X)vertex): " + vertexWeights);
            // Movement weights cannot be reliably calculated
            System.out.println("Movement Weights (w(X)mov): Cannot be fully calculated due to cycle.");
        } else {
            System.out.println("\nNo cyclic dependency detected. All classes sorted topologically.");

            // --- 5. Calculate w(X)mov using the new recursive formula in reverse topological order ---
            Map<Class<?>, Long> movementWeights = new HashMap<>();
            
            // Reverse the topologically sorted list
            List<Class<?>> reversedSortedClasses = new ArrayList<>(sortedClasses);
            Collections.reverse(reversedSortedClasses);

            for (Class<?> targetClass : reversedSortedClasses) { // X in w(X)mov
                long initialWeight = vertexWeights.get(targetClass); // w(X)vertex
                long totalDependentContribution = 0;

                // Iterate through classes that *directly depend on* targetClass (X). These are the 'Yi's.
                // 'dependencies' map stores P -> {C1, C2...} where C depends on P.
                // So, if targetClass is P, dependencies.get(targetClass) gives us {C1, C2...}, which are the Yi's.
                Set<Class<?>> directDependentsOfX = dependencies.get(targetClass);
                if (directDependentsOfX != null) {
                    for (Class<?> Yi : directDependentsOfX) {
                        // For each direct dependent Yi, add its vertex weight and its movement weight.
                        // By iterating in reverse topological order, Yi's movementWeight will already be calculated.
                        totalDependentContribution += vertexWeights.get(Yi); // w(Yi)vertex
                        totalDependentContribution += movementWeights.get(Yi); // w(Yi)mov (already computed)
                    }
                }
                movementWeights.put(targetClass, initialWeight + totalDependentContribution);
            }

            // --- 6. Print Ordered List ---
            System.out.println("\n--- Class Order by Precedence (Tie-broken by Movement Weight) ---");
            // Sort for printing using final movement weights and initial in-degrees
            List<Class<?>> finalDisplaySortedClasses = new ArrayList<>(allClasses);
            Collections.sort(finalDisplaySortedClasses, (c1, c2) -> {
                // Primary sorting by initial in-degree (lower in-degree comes first)
                int inDegreeComparison = initialInDegrees.get(c1).compareTo(initialInDegrees.get(c2));
                if (inDegreeComparison != 0) {
                    return inDegreeComparison;
                }

                // Secondary sorting (tie-break) by movement weight (lighter w(X)mov comes first)
                long wMov1 = movementWeights.getOrDefault(c1, 0L); 
                long wMov2 = movementWeights.getOrDefault(c2, 0L);
                int movementWeightComparison = Long.compare(wMov1, wMov2);
                if (movementWeightComparison != 0) {
                    return movementWeightComparison;
                }
                // Tertiary tie-break by vertex weight (lighter w(X)vertex)
                long wVertex1 = vertexWeights.getOrDefault(c1, 0L);
                long wVertex2 = vertexWeights.getOrDefault(c2, 0L);
                int vertexWeightComparison = Long.compare(wVertex1, wVertex2);
                if (vertexWeightComparison != 0) {
                    return vertexWeightComparison;
                }
                // Final tie-break by class name for deterministic order
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            });


            for (Class<?> clazz : finalDisplaySortedClasses) {
                System.out.println(clazz.getSimpleName() + 
                                   " | Vertex Weight: " + vertexWeights.get(clazz) + 
                                   " | Movement Weight: " + movementWeights.get(clazz) +
                                   " | Initial In-Degree: " + (initialInDegrees.get(clazz) != null ? initialInDegrees.get(clazz) : 0) + 
                                   " | Final In-Degree: " + (currentInDegrees.get(clazz) != null ? currentInDegrees.get(clazz) : 0) 
                                   );
            }

            System.out.println("\n--- Detailed Weights ---");
            System.out.println("Vertex Weights (w(X)vertex): " + vertexWeights);
            System.out.println("Movement Weights (w(X)mov): " + movementWeights);
        }
        
        System.out.println("\n--- Assumptions Used ---");
        System.out.println("- Basic Types: Long (" + LONG_BYTES + "B), UUID (" + UUID_BYTES + "B), Integer (" + INTEGER_BYTES + "B), Boolean (" + BOOLEAN_BYTES + "B)");
        System.out.println("- String with @MyColumn (no explicit length) or unannotated: " + STRING_DEFAULT_BYTES + " bytes");
        System.out.println("- String with @MyColumn(length=n): uses n bytes.");
        System.out.println("- String with @MyColumn(columnDefinition='VARCHAR(n)'): tries to parse n, else " + STRING_DEFAULT_BYTES + " bytes.");
        System.out.println("- Foreign Keys (other BaseEntity types as fields): " + FOREIGN_KEY_BYTES + " bytes (assumed Long ID)");
        System.out.println("- Newline character: " + NEWLINE_BYTES + " byte (per instance)");
        System.out.println("- List/Collection/Array fields: 0 bytes (excluded from row size)");
        System.out.println("- This prototype considers fields declared directly in the class and its superclasses implementing `BaseEntity`.");
        System.out.println("- Precedence: If Class C has @MyManyToOne/@MyOneToOne to Class P, then P precedes C. @MyNotNull implies absolute precedence.");
        System.out.println("- w(X)mov = w(X)vertex + SUM(w(Yi)vertex + w(Yi)mov for all Yi that have a direct @MyManyToOne/@MyOneToOne dependency on X)");
    }
}