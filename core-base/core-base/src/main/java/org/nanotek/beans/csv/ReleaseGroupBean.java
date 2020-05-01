package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseGroup;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseReleaseGroupBean;
import org.nanotek.entities.BaseReleaseGroupCommentBean;
import org.nanotek.entities.BaseReleaseGroupPrimaryTypeBean;
import org.nanotek.proxy.ProxyBase;


public class ReleaseGroupBean 
<K extends BaseBean<ReleaseGroupBean<K>,ReleaseGroup<?>>> 
extends ProxyBase<ReleaseGroupBean<K>,ReleaseGroup<?>>
implements BaseReleaseGroupBean<ReleaseGroupBean<K>>{


	private static final long serialVersionUID = -1119657398190391884L;

	private BaseArtistCreditBean<?> artistCredit;
	
	private BaseReleaseGroupPrimaryTypeBean<?> releaseGroupPrimaryType;
	
	private BaseReleaseGroupCommentBean<?> releaseGroupComment;
	
	public ReleaseGroupBean() { 
		super(castClass());
		postConstruct();
	}
	

	private void postConstruct() {
		artistCredit = new ArtistCreditBean<>();
		releaseGroupPrimaryType = new ReleaseGroupPrimaryTypeBean<>();
		releaseGroupComment = new ReleaseGroupCommentBean<>() ;
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseGroup<?>>castClass() {
		return (Class<? extends ReleaseGroup<?>>) 
				ReleaseGroup.class.
				asSubclass(ReleaseGroup.class);
	}

	public ReleaseGroupBean(Class<? extends ReleaseGroup<?>> class1) {
		super(class1);
		postConstruct();
	}

	public BaseArtistCreditBean<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(BaseArtistCreditBean<?> artistCredit) {
		this.artistCredit = artistCredit;
	}

	@Override
	public void setReleaseGroupPrimaryType(BaseReleaseGroupPrimaryTypeBean<?> k) {
		this.releaseGroupPrimaryType = k;
		
	}

	@Override
	public BaseReleaseGroupPrimaryTypeBean<?> getReleaseGroupPrimaryType() {
		return releaseGroupPrimaryType;
	}


	public BaseReleaseGroupCommentBean<?> getReleaseGroupComment() {
		return releaseGroupComment;
	}


	public void setReleaseGroupComment(BaseReleaseGroupCommentBean<?> releaseGroupComment) {
		this.releaseGroupComment = releaseGroupComment;
	}
	
}
