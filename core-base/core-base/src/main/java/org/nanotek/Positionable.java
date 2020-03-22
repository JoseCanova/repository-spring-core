package org.nanotek;

import java.io.Serializable;

public interface Positionable<P extends Serializable>{

	P getPosition();
	
}
