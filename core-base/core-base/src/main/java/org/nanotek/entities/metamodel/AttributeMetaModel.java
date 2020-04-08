package org.nanotek.entities.metamodel;

import javax.persistence.metamodel.Attribute;

public class AttributeMetaModel<X>{

	private String name;

	private Class<X> attributeClass;

	private Attribute<?,X>metaModelAttibute;

	public AttributeMetaModel(String name, Class<X> attributeClass, Attribute<?, X> metaModelAttibute) {
		super();
		this.name = name;
		this.attributeClass = attributeClass;
		this.metaModelAttibute = metaModelAttibute;
	}

	public AttributeMetaModel() {
		super();
	}

	public AttributeMetaModel(String name, Class<X> attribute) {
		super();
		this.attributeClass = attribute;
		this.name = name;
	}

	public Class<?> getAttributeClass() {
		return attributeClass;
	}

	public String getName() {
		return name;
	}

	public Attribute<?, X> getMetaModelAttibute() {
		return metaModelAttibute;
	}

	public void setMetaModelAttibute(Attribute<?, X> metaModelAttibute) {
		this.metaModelAttibute = metaModelAttibute;
	}

}
