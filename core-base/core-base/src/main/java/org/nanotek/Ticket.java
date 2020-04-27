package org.nanotek;

import java.util.stream.Stream;

public interface Ticket<B extends Base<?> , T extends Base<?>> extends Base<T>{

	default Stream<?> withTicket(B message){
		return Stream.of(message);
	}
	
}
