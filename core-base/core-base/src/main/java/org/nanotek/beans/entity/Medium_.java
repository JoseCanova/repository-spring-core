package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.118-0200")
@StaticMetamodel(Medium.class)
public class Medium_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Medium, Long> mediumId;
	public static volatile SingularAttribute<Medium, String> mediumName;
	public static volatile SingularAttribute<Medium, MediumCount> mediumCount;
	public static volatile SingularAttribute<Medium, MediumPosition> mediumPosition;
	public static volatile SingularAttribute<Medium, Release> release;
	public static volatile SingularAttribute<Medium, MediumFormat> mediumFormat;
}
