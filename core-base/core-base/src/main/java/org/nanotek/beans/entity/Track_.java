package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.366-0200")
@StaticMetamodel(Track.class)
public class Track_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Track, Long> trackId;
	public static volatile SingularAttribute<Track, UUID> gid;
	public static volatile SingularAttribute<Track, String> trackName;
	public static volatile SingularAttribute<Track, TrackPosition> trackPosition;
	public static volatile SingularAttribute<Track, TrackNumber> trackNumber;
	public static volatile SingularAttribute<Track, TrackLength> trackLength;
	public static volatile SingularAttribute<Track, Recording> recording;
}
