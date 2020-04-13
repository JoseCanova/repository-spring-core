package org.nanotek.entities;

import java.io.Serializable;

public interface MutableGenderEntity<K> extends GenderEntity<K>{
			void setGender(K k);
}
