package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.InstrumentComment;
import org.nanotek.entities.BaseInstrumentCommentBean;
import org.nanotek.proxy.ProxyBase;

public class InstrumentCommentBean<K extends BaseBean<InstrumentCommentBean<K>,InstrumentComment<?>>> 
extends ProxyBase<InstrumentCommentBean<K>, InstrumentComment<?>>
implements BaseInstrumentCommentBean<InstrumentCommentBean<K>>{

	private static final long serialVersionUID = 1822351369658880980L;

	public InstrumentCommentBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends InstrumentComment<?>> castClass() {
		return (Class<? extends InstrumentComment<?>>) 
				InstrumentComment.class.
				asSubclass(InstrumentComment.class);
	}
	
	
	public InstrumentCommentBean(Class<? extends InstrumentComment<?>> class1) {
		super(class1);
	}

}
