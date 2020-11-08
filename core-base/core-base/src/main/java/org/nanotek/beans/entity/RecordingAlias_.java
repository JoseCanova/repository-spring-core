package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.168-0200")
@StaticMetamodel(RecordingAlias.class)
public class RecordingAlias_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<RecordingAlias, Long> recordingAliasId;
	public static volatile SingularAttribute<RecordingAlias, String> locale;
	public static volatile SingularAttribute<RecordingAlias, String> recordingAliasName;
	public static volatile SingularAttribute<RecordingAlias, RecordingAliasSortName> recordingAliasSortName;
	public static volatile SingularAttribute<RecordingAlias, Recording> recording;
	public static volatile SingularAttribute<RecordingAlias, RecordingAliasType> type;
	public static volatile SingularAttribute<RecordingAlias, Integer> beginDateYear;
	public static volatile SingularAttribute<RecordingAlias, Integer> beginDateMonth;
	public static volatile SingularAttribute<RecordingAlias, Integer> beginDateDay;
	public static volatile SingularAttribute<RecordingAlias, Integer> endDateYear;
	public static volatile SingularAttribute<RecordingAlias, Integer> endDateMonth;
	public static volatile SingularAttribute<RecordingAlias, Integer> endDateDay;
}
