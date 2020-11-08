package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.402-0200")
@StaticMetamodel(TypeNamedEntity.class)
public class TypeNamedEntity_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<TypeNamedEntity, UUID> gid;
	public static volatile SingularAttribute<TypeNamedEntity, String> typeName;
}
