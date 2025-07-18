package org.nanotek.opencsv.service;

import java.io.Serializable;
import java.util.Objects;

/**
 * A simple immutable generic Pair class to hold two values.
 * Useful for returning multiple related values from a method.
 *
 * @param <V1> The type of the first value.
 * @param <V2> The type of the second value.
 */
public class Pair<V1, V2> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final V1 first;
    private final V2 second;

    public Pair(V1 first, V2 second) {
        this.first = first;
        this.second = second;
    }

    public V1 getFirst() {
        return first;
    }

    public V2 getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
               Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
               "first=" + first +
               ", second=" + second +
               '}';
    }

    /**
     * Convenience static factory method to create a Pair.
     *
     * @param first The first value.
     * @param second The second value.
     * @param <V1> The type of the first value.
     * @param <V2> The type of the second value.
     * @return A new Pair instance.
     */
    public static <V1, V2> Pair<V1, V2> of(V1 first, V2 second) {
        return new Pair<>(first, second);
    }
}