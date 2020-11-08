package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.134-0200")
@StaticMetamodel(MediumFormat.class)
public class MediumFormat_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<MediumFormat, Long> mediumFormatId;
	public static volatile SingularAttribute<MediumFormat, String> mediumFormatName;
	public static volatile SingularAttribute<MediumFormat, Long> parent;
	public static volatile SingularAttribute<MediumFormat, Integer> year;
	public static volatile SingularAttribute<MediumFormat, String> discId;
	public static volatile SingularAttribute<MediumFormat, UUID> gid;
	public static volatile SingularAttribute<MediumFormat, String> description;
}
