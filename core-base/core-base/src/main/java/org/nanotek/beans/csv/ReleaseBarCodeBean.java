package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseBarCode;
import org.nanotek.entities.BaseReleaseBarCodeBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseBarCodeBean 
<K extends BaseBean<ReleaseBarCodeBean<K>,ReleaseBarCode<?>>> 
extends ProxyBase<ReleaseBarCodeBean<K>,ReleaseBarCode<?>>
implements BaseReleaseBarCodeBean<ReleaseBarCodeBean<K>>{


	private static final long serialVersionUID = -5653516361665353129L;

	public ReleaseBarCodeBean(Class<? extends ReleaseBarCode<?>> class1) {
		super(class1);
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseBarCode<?>> castClass() {
		return (Class<? extends ReleaseBarCode<?>>) 
				ReleaseBarCode.class.asSubclass(ReleaseBarCode.class);
	}
	
	public ReleaseBarCodeBean() {
		super(castClass());
	}

}
