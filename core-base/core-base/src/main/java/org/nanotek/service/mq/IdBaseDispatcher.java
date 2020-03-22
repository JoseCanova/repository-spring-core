package org.nanotek.service.mq;

import java.util.concurrent.Future;

import org.nanotek.IdBase;
import org.nanotek.ReturnableDispatcher;

public class IdBaseDispatcher<K extends IdBase<K,?>, ID  extends Future<ID>> implements ReturnableDispatcher<K,ID> {

	public IdBaseDispatcher() {
	}

	@Override
	public ID dispatch(K bean) {
		return null;
	}

}
