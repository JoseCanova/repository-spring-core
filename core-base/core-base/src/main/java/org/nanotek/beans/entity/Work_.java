package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.410-0200")
@StaticMetamodel(Work.class)
public class Work_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Work, Long> workId;
	public static volatile SingularAttribute<Work, UUID> gid;
	public static volatile SingularAttribute<Work, WorkType> workType;
	public static volatile SingularAttribute<Work, String> workName;
	public static volatile SingularAttribute<Work, WorkComment> workComment;
}
