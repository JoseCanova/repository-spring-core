package org.nanotek.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.nanotek.IdBase;

public class IdBaseList<K extends IdBase<?, ?>> implements BaseList<K>{

	private ArrayList<K> list;
	
	public IdBaseList() {
		list = new ArrayList<>();
	}

	public void add(int index, K element) {
		list.add(index, element);
	}

	public boolean add(K e) {
		return list.add(e);
	}

	public boolean addAll(Collection<? extends K> c) {
		return list.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends K> c) {
		return list.addAll(index, c);
	}

	public void clear() {
		list.clear();
	}

	public Object clone() {
		return list.clone();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}

	public void ensureCapacity(int minCapacity) {
		list.ensureCapacity(minCapacity);
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}

	public void forEach(Consumer<? super K> arg0) {
		list.forEach(arg0);
	}

	public K get(int index) {
		return list.get(index);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator<K> iterator() {
		return list.iterator();
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<K> listIterator() {
		return list.listIterator();
	}

	public ListIterator<K> listIterator(int index) {
		return list.listIterator(index);
	}

	public Stream<K> parallelStream() {
		return list.parallelStream();
	}

	public K remove(int index) {
		return list.remove(index);
	}

	public boolean remove(Object o) {
		return list.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public boolean removeIf(Predicate<? super K> filter) {
		return list.removeIf(filter);
	}

	public void replaceAll(UnaryOperator<K> operator) {
		list.replaceAll(operator);
	}

	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public K set(int index, K element) {
		return list.set(index, element);
	}

	public int size() {
		return list.size();
	}

	public void sort(Comparator<? super K> c) {
		list.sort(c);
	}

	public Spliterator<K> spliterator() {
		return list.spliterator();
	}

	public Stream<K> stream() {
		return list.stream();
	}

	public List<K> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(IntFunction<T[]> generator) {
		return list.toArray(generator);
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public String toString() {
		return list.toString();
	}

	public void trimToSize() {
		list.trimToSize();
	}
    
	

}
