package org.nanotek.apachemq;

import java.util.stream.Stream;

import org.nanotek.Base;
import org.nanotek.stream.KongStream;

public interface Ticket<B extends Base<?> , T extends Base<?>> extends Base<T>{

	default Stream<?> withTicket(B message){
		return KongStream.of(message.getClass().asSubclass(Base.class)).add(message).build();
	}
	
}
