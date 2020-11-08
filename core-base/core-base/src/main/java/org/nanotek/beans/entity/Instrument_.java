package org.nanotek.beans.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:10.993-0200")
@StaticMetamodel(Instrument.class)
public class Instrument_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Instrument, Long> instrumentId;
	public static volatile SingularAttribute<Instrument, UUID> gid;
	public static volatile SingularAttribute<Instrument, String> instrumentName;
	public static volatile SingularAttribute<Instrument, InstrumentType> instrumentType;
	public static volatile SingularAttribute<Instrument, InstrumentComment> instrumentComment;
	public static volatile SingularAttribute<Instrument, InstrumentDescription> instrumentDescription;
}
