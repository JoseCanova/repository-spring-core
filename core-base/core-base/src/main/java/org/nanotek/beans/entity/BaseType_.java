package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.856-0200")
@StaticMetamodel(BaseType.class)
public class BaseType_ extends TypeNamedEntity_ {
	public static volatile SingularAttribute<BaseType, Long> typeId;
	public static volatile SingularAttribute<BaseType, Long> parent;
	public static volatile SingularAttribute<BaseType, Long> childOrder;
	public static volatile SingularAttribute<BaseType, BaseTypeDescription> baseTypeDescription;
}
