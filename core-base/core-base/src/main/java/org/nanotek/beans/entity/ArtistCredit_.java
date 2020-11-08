package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.760-0200")
@StaticMetamodel(ArtistCredit.class)
public class ArtistCredit_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ArtistCredit, Long> artistCreditId;
	public static volatile SingularAttribute<ArtistCredit, String> artistCreditName;
	public static volatile SingularAttribute<ArtistCredit, ArtistCreditCount> artistCreditCount;
	public static volatile SingularAttribute<ArtistCredit, ArtistCreditRefCount> artistCreditRefCount;
	public static volatile SetAttribute<ArtistCredit, Release> releases;
	public static volatile ListAttribute<ArtistCredit, Artist> artists;
	public static volatile SetAttribute<ArtistCredit, Recording> recordings;
}
