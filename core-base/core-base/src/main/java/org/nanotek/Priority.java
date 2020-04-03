package org.nanotek;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.Artist;

public interface Priority<E extends Base<?>,P extends Comparable<?>> extends Comparator<P> {

	default E getElement()
	{ 
		return null;
	}

	default P getPriority() {
		return null;
	}

	@Override
	default int compare(P o1, P o2) {
		return 0;
	}

	static <V extends Priority<E,P>,E extends Base<?>,P extends Comparable<P>> 
	Priority<E,P> createPriorityElement(E element,P priority) {
		return new Priority<E,P>() {

			private P priorityValue = priority;

			private E elementValue = element;


			public P  getPriority() {
				return priorityValue;
			}

			public E getElement() {
				return elementValue;
			}
			@Override
			public int compare(P o1, P o2) {
				return o1.compareTo(o2);
			}
		};
	}

	class PriorityComparator<C extends Comparable<C>> implements Comparator<Priority<?,C>>{

		@Override
		public int compare(Priority<?, C> o1, Priority<?, C> o2) {
			return o2.getPriority().compareTo(o1.getPriority());
		}

	}
	
	public static <V extends Priority<E,P>,E extends Base<?>,P extends Comparable<?>>   void main(String[] args) { 
		Priority<E,Integer> artistPriority = Priority.createPriorityElement(new Artist(), 10);
		Priority<E,Integer> areaPriority = Priority.createPriorityElement(new Area(), 80);
		Priority<E,Integer> areaTypePriority = Priority.createPriorityElement(new AreaType(), 39);
		PriorityQueue<Priority<E,Integer>> pq = new PriorityQueue<Priority<E,Integer>>(new PriorityComparator<Integer>());
		boolean result = pq.offer(artistPriority);	
		System.out.println(result);
		result =  pq.offer(areaPriority);	
		System.out.println(result);
		result = pq.offer(areaTypePriority);	
		System.out.println(result);
		System.out.println(pq.peek().getElement().toString());
	}
}
