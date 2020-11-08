package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.556-0200")
@StaticMetamodel(Area.class)
public class Area_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Area, Long> areaId;
	public static volatile SingularAttribute<Area, UUID> gid;
	public static volatile SingularAttribute<Area, AreaType> areaType;
	public static volatile SingularAttribute<Area, AreaBeginDate> areaBeginDate;
	public static volatile SingularAttribute<Area, AreaEndDate> areaEndDate;
	public static volatile SingularAttribute<Area, AreaComment> areaComment;
	public static volatile SingularAttribute<Area, String> areaName;
}
