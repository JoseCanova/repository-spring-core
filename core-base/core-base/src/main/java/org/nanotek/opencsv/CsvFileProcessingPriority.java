package org.nanotek.opencsv;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.PluralAttribute;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.hibernate.metamodel.model.domain.spi.EntityTypeDescriptor;
import org.hibernate.persister.entity.EntityPersister;
import org.jgrapht.Graph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.nanotek.BaseEntity;
import org.nanotek.Priority;
import org.nanotek.entities.metamodel.BrainzGraphModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvFileProcessingPriority<K extends BaseEntity<?,?>> 
implements Priority<K,Integer>{

	@Autowired
	BrainzGraphModel brainzGraphModel;

	public CsvFileProcessingPriority() {
	}

	public List<Priority<?,Integer>> generatePriorities(){

		Map<Class<?>,Priority<?,Integer>> priorityMap = new HashMap<Class<?>,Priority<?,Integer>>();
		brainzGraphModel.
		getEntityDirectedGraph()
		.vertexSet().stream().map(v->{
			Priority<?,Integer> p = generatePriorityForElement(castV(v));
			return p;
		})
		.forEach(p->priorityMap.put((Class<?>)p.getElement(),p));
		//		

		processGraphByBreadthFirst(priorityMap);


		//		brainzGraphModel.
		//		getEntityDirectedGraph()
		//		.edgeSet().stream().forEach(v->{
		//			PriorityEdge pe= PriorityEdge.class.cast(v);
		//			Priority<?,Integer> sourcePriority = priorityMap.get(pe.getSource());
		//			Priority<?,Integer> targetPriority = priorityMap.get(pe.getTarget());
		//			if (targetPriority.getPriority() <= sourcePriority.getPriority()) {
		//				Integer newTargetPriorityValue = 1;
		//				Integer newSourcePriorityValue = -1;
		//				Priority<?,Integer> newTargetPriority = Priority.createPriorityElement(pe.getTarget(), 
		//						newTargetPriorityValue);
		//				Priority<?,Integer> newSourcePriority = Priority.createPriorityElement(pe.getSource(), 
		//						newSourcePriorityValue);
		//				System.out.println(sourcePriority.getElement() + " " + newTargetPriority.getElement());
		//				priorityMap.put((Class<?>)newTargetPriority.getElement(), newTargetPriority);
		//				priorityMap.put((Class<?>)newSourcePriority.getElement(), newSourcePriority);
		//			}
		////				priorityMap.put((Class<?>)newSourcePriority.getElement(), newSourcePriority);
		//		});




		//			//Penalization phase.
		//			brainzGraphModel.
		//					getEntityDirectedGraph()
		//					.edgeSet()
		//					.stream()
		//					.forEach(ep->{
		//						PriorityEdge pe = PriorityEdge.class.cast(ep);
		//						priorityMap.keySet().stream().forEach(p->{
		//							if(pe.getSource().equals(priorityMap.get(p).getElement())) {
		//								MetamodelImpl metaModel = brainzGraphModel.getMetaModel();
		//								
		//								Priority<?,Integer> prior = priorityMap
		//															.getOrDefault(pe.getTarget(), generatePriorityForElement(pe.getTarget(), 0));
		//								Priority<?,Integer> pprior = priorityMap.get(p);
		//								Integer finalPriority= pprior.getPriority() + prior.getPriority();
		//								Priority<?,Integer> fp = Priority.createPriorityElement((Class<?>)pprior.getElement(), finalPriority);
		//								
		//								priorityMap.put((Class<?>)fp.getElement(), fp);
		//								
		//								EntityTypeDescriptor<?> ped = metaModel.entity((Class<?>)pe.getSource());
		//								EntityTypeDescriptor<?> ted = metaModel.entity((Class<?>)pe.getTarget());
		//								System.out.println("[ped]"+ped.getName());
		//								System.out.println("[ted1]"+ted.getName());
		//								EntityPersister seper = metaModel.entityPersister((Class<?>)pe.getSource());
		//								EntityPersister teper = metaModel.entityPersister((Class<?>)pe.getTarget());
		//								System.out.println("");
		//							}
		//						});
		//					});




		return priorityMap.values().stream().collect(Collectors.toList());
	}


	private Attribute<?, ?> getAssociation(EntityTypeDescriptor<?> ped, EntityTypeDescriptor<?> ted) {
		ped.getAttributes()
		.stream()
		.forEach(a ->{
			if(a.isAssociation()) {
				if (a.isCollection()) {
					PluralAttribute<?,?,?> pa = PluralAttribute.class.cast(a);
					EntityTypeDescriptor<?> ted1 = ted;
					System.out.println("[ped]"+ped.getName());
					System.out.println("[et]"+pa.getElementType().getJavaType().getName());
					System.out.println("[ted1]"+ted1.getJavaType().getName());
					if(pa.getElementType().getJavaType().getName().equals(ted1.getJavaType().getName())) {
						pa.getCollectionType();
						System.out.println("Equals");
					}
				}
			}
		});


		return null;
	}

	private Class<K> castV(Class<? extends BaseEntity> v) {
		return (Class<K>) v;
	}

	public <V,E> void processGraphByBreadthFirst(Map<Class<?>,Priority<?,Integer>> priorityMap){
		Priority p = priorityMap.values().stream().max((v1,v2)->new Priority.PriorityComparator().compare(v2,v1)).get();
		System.out.println("");		
				
		
		brainzGraphModel.getEntityDirectedGraph().vertexSet().forEach(v->{
		
		BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>
		iterator = brainzGraphModel.getBreadthFirstIterator((Class<? extends BaseEntity>)v);
		while (iterator.hasNext()) { 
			Class<? extends BaseEntity> next = iterator.next();
			Class<? extends BaseEntity> parent = iterator.getParent(next);
			if(brainzGraphModel.getEntityDirectedGraph().containsEdge(parent , next)) {
				PriorityEdge edge = (PriorityEdge) brainzGraphModel.getEntityDirectedGraph().getEdge(next, parent);
				Priority<?,Integer> pnext=priorityMap.get(next); 
				Priority<?,Integer> pparent=priorityMap.get(parent);
				if(pparent.getPriority()>=pnext.getPriority()) { 
					Priority<?,Integer> pnextP =  Priority.createPriorityElement(next, pparent.getPriority()+pnext.getPriority() +1);
					Priority<?,Integer> pparentP =  Priority.createPriorityElement(parent, pnext.getPriority() + 1);
					priorityMap.put(parent, pparentP);
					priorityMap.put(next, pnextP);
				}
			}

		}});
	}

	public Priority<?, Integer> generatePriorityForElement(Class<K> element) {

		Integer priority = brainzGraphModel
				.getEntityDirectedGraph()
				.edgeSet()
				.stream()
				.map(ep -> {
					PriorityEdge pe= PriorityEdge.class.cast(ep);
					int res=0;
					if(pe.getSource().equals(element))
						res = 1;
					return res;})
				.reduce(0, Integer::sum);
		return generatePriorityForElement(element,priority );
	}


	private <V extends Priority<E,P>,E,P extends Comparable<P>> 
	Priority<?,P> generatePriorityForElement(E element,P priority) {
		return  new Priority<E,P>() {

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

}
