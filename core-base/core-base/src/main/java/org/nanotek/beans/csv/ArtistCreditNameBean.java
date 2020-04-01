package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistComment;
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
		super( castClass());
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditName<?>> castClass() {
		return (Class<? extends ArtistCreditName<?>>) 
				ArtistCreditName.class.
				asSubclass(ArtistCreditName.class);
	}
	
	
	public ArtistCreditNameBean(Class<? extends ArtistCreditName<?>> class1) {
		super(class1);
		// TODO Auto-generated constructor stub
	}

	
}
