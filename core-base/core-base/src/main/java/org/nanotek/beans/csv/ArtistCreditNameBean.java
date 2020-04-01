package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.entities.BaseArtistCreditNameBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditNameBean
<K extends BaseBean<ArtistCreditNameBean<K>,ArtistCreditName<?>>> 
extends ProxyBase<ArtistCreditNameBean<K>,ArtistCreditName<?>>
implements BaseArtistCreditNameBean<ArtistCreditNameBean<K>>{


	private static final long serialVersionUID = -625201514069517695L;

//	private Long artistCreditId;
//	
//	private Long position;
//	
//	private Long artistId; 
//	
//	private String name; 
//	
//	private String joinPhrase;

	
	public ArtistCreditNameBean() {
		super((Class<? extends ArtistCreditName<?>>) ArtistCreditName.class);
		// TODO Auto-generated constructor stub
	}
	
	public ArtistCreditNameBean(Class<? extends ArtistCreditName<?>> class1) {
		super(class1);
		// TODO Auto-generated constructor stub
	}

	
}
