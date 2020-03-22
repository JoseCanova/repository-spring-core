package org.nanotek;

import java.io.Serializable;

public interface ImmutableLengthIdBase<K extends IdBase<K,ID>,  L extends Serializable , ID extends Serializable> extends IdBase<K,ID>{
	L getLength();
}
