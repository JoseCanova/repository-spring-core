package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.665-0200")
@StaticMetamodel(ArtistAlias.class)
public class ArtistAlias_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ArtistAlias, String> artistAliasName;
	public static volatile SingularAttribute<ArtistAlias, Long> aliasId;
	public static volatile SingularAttribute<ArtistAlias, ArtistAliasSortName> artistAliasSortName;
	public static volatile SingularAttribute<ArtistAlias, Artist> artist;
	public static volatile SingularAttribute<ArtistAlias, ArtistAliasLocale> artistAliasLocale;
	public static volatile SingularAttribute<ArtistAlias, ArtistAliasType> artistAliasType;
	public static volatile SingularAttribute<ArtistAlias, ArtistAliasBeginDate> artistAliasBeginDate;
	public static volatile SingularAttribute<ArtistAlias, ArtistAliasEndDate> artistAliasEndDate;
}
