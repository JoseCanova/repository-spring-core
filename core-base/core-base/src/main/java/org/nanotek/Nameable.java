package org.nanotek;

import java.io.Serializable;

public interface Nameable<K> extends  Serializable {
	K getName();
}
