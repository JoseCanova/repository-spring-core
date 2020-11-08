package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.312-0200")
@StaticMetamodel(ReleaseLabel.class)
public class ReleaseLabel_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<ReleaseLabel, Long> releaseLabelId;
	public static volatile SingularAttribute<ReleaseLabel, Release> release;
	public static volatile SingularAttribute<ReleaseLabel, Label> labelRelease;
	public static volatile SingularAttribute<ReleaseLabel, ReleaseLabelCatalog> releaseLabelCatalog;
}
