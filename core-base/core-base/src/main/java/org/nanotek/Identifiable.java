package org.nanotek;

import java.io.Serializable;

public interface Identifiable<T extends ImmutableBase<T,ID>,ID extends Serializable> extends ImmutableBase <T,ID>{	
}
