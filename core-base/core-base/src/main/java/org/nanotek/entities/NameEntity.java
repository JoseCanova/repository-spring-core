package org.nanotek.entities;

import java.io.Serializable;

public interface NameEntity<K extends Serializable> {
	K getName();
}
