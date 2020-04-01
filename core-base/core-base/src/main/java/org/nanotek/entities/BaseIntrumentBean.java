package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.InstrumentBean;
import org.nanotek.beans.entity.Instrument;

public interface BaseIntrumentBean<K extends BaseBean<K,Instrument<?>>> 
extends Base<K>,
BaseBean<K,Instrument<?>>
,MutableNameEntity<String>
,MutableGidEntity<UUID>{

	
    @Override
    default UUID getGid() {
    	return read(GidEntity.class).map(m->UUID.class.cast(m)).orElse(null);
    }
    
    
    @Override
    default void setGid(UUID k) {
    	write(MutableGidEntity.class,k);
    }
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	@Override	
	default String getName() {
		return read(NameEntity.class).filter(v-> v!=null).map(v -> String.class.cast(v)).orElse(new String());
	}
	
	
	public static void main(String[] args) {
		InstrumentBean<?> bean = new InstrumentBean<>();
	}
}
