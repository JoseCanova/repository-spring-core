package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistComment;
import org.nanotek.entities.BaseArtistCommentBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCommentBean
<K extends BaseBean<ArtistCommentBean<K>,ArtistComment<?>>> 
extends ProxyBase<ArtistCommentBean<K>, ArtistComment<?>>
implements BaseArtistCommentBean<ArtistCommentBean<K>>{

	private static final long serialVersionUID = 1822351369658880980L;

	public ArtistCommentBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistComment<?>> castClass() {
		return (Class<? extends ArtistComment<?>>) 
				ArtistComment.class.
				asSubclass(ArtistComment.class);
	}
	
	
	public ArtistCommentBean(Class<? extends ArtistComment<?>> class1) {
		super(class1);
	}
	
}
