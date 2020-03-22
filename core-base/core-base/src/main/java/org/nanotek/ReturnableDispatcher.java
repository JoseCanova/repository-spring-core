package org.nanotek;

import java.util.concurrent.Future;

@FunctionalInterface
public interface ReturnableDispatcher<K extends Base<?>, ID extends Future<ID>>  {

	 ID dispatch (K bean);
	
}
