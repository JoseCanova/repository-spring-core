package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.032-0200")
@StaticMetamodel(Label.class)
public class Label_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Label, Long> labelId;
	public static volatile SingularAttribute<Label, UUID> gid;
	public static volatile SingularAttribute<Label, String> labelName;
	public static volatile SingularAttribute<Label, LabelType> labelType;
	public static volatile SingularAttribute<Label, Integer> labelCode;
	public static volatile SingularAttribute<Label, LabelBeginDate> labelBeginDate;
	public static volatile SingularAttribute<Label, LabelEndDate> labelEndDate;
	public static volatile SingularAttribute<Label, Area> area;
}
