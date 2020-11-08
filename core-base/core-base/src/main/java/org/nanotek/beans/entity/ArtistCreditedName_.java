package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.780-0200")
@StaticMetamodel(ArtistCreditedName.class)
public class ArtistCreditedName_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ArtistCreditedName, String> artistCreditedName;
	public static volatile SingularAttribute<ArtistCreditedName, ArtistCredit> artistCredit;
	public static volatile SingularAttribute<ArtistCreditedName, Artist> artist;
	public static volatile SingularAttribute<ArtistCreditedName, ArtistCreditedNamePosition> artistCreditNamePosition;
	public static volatile SingularAttribute<ArtistCreditedName, String> artistCreditNameJoinPhrase;
}
