package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.215-0200")
@StaticMetamodel(ReleaseAlias.class)
public class ReleaseAlias_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ReleaseAlias, Long> releaseAliasId;
	public static volatile SingularAttribute<ReleaseAlias, String> releaseAliasName;
	public static volatile SingularAttribute<ReleaseAlias, ReleaseAliasLocale> releaseAliasLocale;
	public static volatile SingularAttribute<ReleaseAlias, ReleaseAliasSortName> releaseAliasSortName;
	public static volatile SingularAttribute<ReleaseAlias, Release> release;
	public static volatile SingularAttribute<ReleaseAlias, ReleaseAliasType> releaseAliasType;
	public static volatile SingularAttribute<ReleaseAlias, ReleaseAliasBeginDate> releaseAliasBeginDate;
	public static volatile SingularAttribute<ReleaseAlias, ReleaseAliasEndDate> releaseAliasEndDate;
}
