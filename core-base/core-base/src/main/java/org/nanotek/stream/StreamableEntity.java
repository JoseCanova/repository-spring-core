package org.nanotek.stream;

import org.nanotek.Base;
import org.springframework.data.util.Streamable;

public interface StreamableEntity<K extends Base<?>> 
	extends Streamable<K>{

}
