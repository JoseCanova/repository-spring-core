package org.nanotek.collections;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.proxy.ProxyBase;

public class IdentityOnCertainAspect
<K extends BaseBean<IdentityOnCertainAspect<K>,Artist<?>>> 
extends ProxyBase<IdentityOnCertainAspect<K>,Artist<?>>
implements ArtistIdentity<IdentityOnCertainAspect<K>>
{

	public IdentityOnCertainAspect(Class<? extends Artist<?>> class1) {
		super(class1);
	}

	public static void main(String[] args) {
		IdentityOnCertainAspect ioc = new IdentityOnCertainAspect(Artist.class);
		ioc.setArtistId(1000L);
		System.out.println(ioc.getArtistId());
	}
	
}