package org.nanotek.proxy.map.bean;

import org.nanotek.Base;

public abstract class ForwardBean <M,B>
implements Base<ForwardBean<M,B>>{

	private static final long serialVersionUID = -5701874727243861860L;

	public ForwardBean() {
	}
	
	abstract M from();
    
	abstract B to();
}
