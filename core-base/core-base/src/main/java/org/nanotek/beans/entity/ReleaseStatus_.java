package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.339-0200")
@StaticMetamodel(ReleaseStatus.class)
public class ReleaseStatus_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ReleaseStatus, Long> releaseStatusId;
	public static volatile SingularAttribute<ReleaseStatus, UUID> gid;
	public static volatile SingularAttribute<ReleaseStatus, String> releaseStatusName;
	public static volatile SetAttribute<ReleaseStatus, Release> releases;
}
