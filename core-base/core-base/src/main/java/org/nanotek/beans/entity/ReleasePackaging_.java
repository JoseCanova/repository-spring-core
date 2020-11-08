package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.327-0200")
@StaticMetamodel(ReleasePackaging.class)
public class ReleasePackaging_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ReleasePackaging, Long> releasePackagingId;
	public static volatile SingularAttribute<ReleasePackaging, UUID> gid;
	public static volatile SingularAttribute<ReleasePackaging, String> releasePackagingName;
	public static volatile SetAttribute<ReleasePackaging, Release> releases;
}
