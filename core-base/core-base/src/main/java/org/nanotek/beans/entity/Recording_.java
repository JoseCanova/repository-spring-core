package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.157-0200")
@StaticMetamodel(Recording.class)
public class Recording_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Recording, Long> recordingId;
	public static volatile SingularAttribute<Recording, UUID> gid;
	public static volatile SingularAttribute<Recording, String> recordingName;
	public static volatile SingularAttribute<Recording, ArtistCredit> artistCredit;
	public static volatile SetAttribute<Recording, Track> tracks;
	public static volatile SingularAttribute<Recording, RecordingLength> recordingLength;
	public static volatile SetAttribute<Recording, Isrc> recordingIsrcs;
	public static volatile SingularAttribute<Recording, RecordingComment> recordingComment;
}
