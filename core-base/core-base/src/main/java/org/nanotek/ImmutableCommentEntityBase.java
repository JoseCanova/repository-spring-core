package org.nanotek;

import java.io.Serializable;

public interface ImmutableCommentEntityBase<K extends IdBase<K,ID>, C extends Serializable, ID extends Serializable> extends IdBase<K,ID>{

	
	C getComment();
	
	
}
