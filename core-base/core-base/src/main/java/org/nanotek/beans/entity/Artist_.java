package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.652-0200")
@StaticMetamodel(Artist.class)
public class Artist_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Artist, Long> artistId;
	public static volatile SingularAttribute<Artist, UUID> gid;
	public static volatile SingularAttribute<Artist, String> artistName;
	public static volatile ListAttribute<Artist, ArtistCredit> artistCredits;
	public static volatile SingularAttribute<Artist, ArtistSortName> artistSortName;
	public static volatile SingularAttribute<Artist, ArtistComment> artistComment;
	public static volatile SingularAttribute<Artist, ArtistBeginDate> artistBeginDate;
	public static volatile SingularAttribute<Artist, ArtistEndDate> artistEndDate;
	public static volatile SingularAttribute<Artist, ArtistType> artistType;
	public static volatile SingularAttribute<Artist, Gender> gender;
	public static volatile SingularAttribute<Artist, Area> area;
	public static volatile SingularAttribute<Artist, Area> beginArea;
	public static volatile SingularAttribute<Artist, Area> endArea;
	public static volatile ListAttribute<Artist, ArtistAlias> artistAlias;
}
