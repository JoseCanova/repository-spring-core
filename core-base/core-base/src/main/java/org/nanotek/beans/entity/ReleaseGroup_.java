package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.288-0200")
@StaticMetamodel(ReleaseGroup.class)
public class ReleaseGroup_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ReleaseGroup, Long> releaseGroupId;
	public static volatile SingularAttribute<ReleaseGroup, UUID> gid;
	public static volatile SingularAttribute<ReleaseGroup, String> releaseGroupName;
	public static volatile SingularAttribute<ReleaseGroup, ArtistCredit> artistCredit;
	public static volatile SingularAttribute<ReleaseGroup, ReleaseGroupPrimaryType> releaseGroupPrimaryType;
	public static volatile SetAttribute<ReleaseGroup, Release> releases;
	public static volatile SingularAttribute<ReleaseGroup, ReleaseGroupComment> releaseGroupComment;
}
