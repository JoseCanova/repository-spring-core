package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.207-0200")
@StaticMetamodel(Release.class)
public class Release_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Release, Long> releaseId;
	public static volatile SingularAttribute<Release, String> releaseName;
	public static volatile SingularAttribute<Release, UUID> gid;
	public static volatile SingularAttribute<Release, Language> language;
	public static volatile SingularAttribute<Release, ArtistCredit> artistCredit;
	public static volatile SingularAttribute<Release, ReleaseBarCode> releaseBarCode;
	public static volatile SingularAttribute<Release, ReleaseComment> releaseComment;
	public static volatile SingularAttribute<Release, ReleaseStatus> releaseStatus;
	public static volatile SingularAttribute<Release, ReleasePackaging> releasePackaging;
	public static volatile SingularAttribute<Release, ReleaseGroup> releaseGroup;
	public static volatile SetAttribute<Release, ReleaseLabel> releaseLabels;
}
