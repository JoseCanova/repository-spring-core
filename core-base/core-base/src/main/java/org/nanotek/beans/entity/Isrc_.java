package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.024-0200")
@StaticMetamodel(Isrc.class)
public class Isrc_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Isrc, Long> isrcId;
	public static volatile SingularAttribute<Isrc, Recording> recording;
	public static volatile SingularAttribute<Isrc, Integer> isrcSource;
	public static volatile SingularAttribute<Isrc, String> isrc;
}
