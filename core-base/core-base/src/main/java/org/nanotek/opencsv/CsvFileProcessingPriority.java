package org.nanotek.opencsv;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.PluralAttribute;

import org.hibernate.metamodel.internal.MetamodelImpl;
import org.hibernate.metamodel.model.domain.spi.EntityTypeDescriptor;
import org.hibernate.persister.entity.EntityPersister;
import org.jgrapht.Graph;
import org.nanotek.BaseEntity;
import org.nanotek.Priority;
import org.nanotek.entities.metamodel.BrainzGraphModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.doclint.Entity;

@Service
public class CsvFileProcessingPriority<K extends BaseEntity<?,?>> 
		implements Priority<K,Integer>{

	@Autowired
	BrainzGraphModel brainzGraphModel;
	
	public CsvFileProcessingPriority() {
	}

	public List<Priority<?,Integer>> generatePriorities(){
		
		
		
		List<Priority<?,Integer>> list = brainzGraphModel.
		getEntityDirectedGraph()
		.vertexSet().stream().map(v->{
			Priority<?,Integer> p = generatePriorityForElement(castV(v));
			return p;
		}).collect(Collectors.toList());
		
		
			
			brainzGraphModel.
					getEntityDirectedGraph()
					.edgeSet()
					.stream()
					.forEach(ep->{
						PriorityEdge pe = PriorityEdge.class.cast(ep);
						list.stream().forEach(p->{
							if(pe.getSource().equals(p.getElement())) {
								MetamodelImpl metaModel = brainzGraphModel.getMetaModel();
								EntityTypeDescriptor<?> ped = metaModel.entity((Class<?>)pe.getSource());
								EntityTypeDescriptor<?> ted = metaModel.entity((Class<?>)pe.getTarget());
								System.out.println("[ped]"+ped.getName());
								System.out.println("[ted1]"+ted.getName());
								EntityPersister seper = metaModel.entityPersister((Class<?>)pe.getSource());
								EntityPersister teper = metaModel.entityPersister((Class<?>)pe.getTarget());
								System.out.println("");
							}
						});
					});
			
			
		
		
		return null;
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

	public Priority<?, Integer> generatePriorityForElement(Class<K> element) {
		
		Integer priority = brainzGraphModel
							.getEntityDirectedGraph()
							.edgeSet()
							.stream()
							.map(ep -> {
									PriorityEdge pe= PriorityEdge.class.cast(ep);
									int res = (pe.getTarget().equals(element)?-1:0);
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
