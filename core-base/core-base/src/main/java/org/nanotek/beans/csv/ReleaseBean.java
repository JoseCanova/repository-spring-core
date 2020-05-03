package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Release;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseLanguageBean;
import org.nanotek.entities.BaseReleaseBarCodeBean;
import org.nanotek.entities.BaseReleaseBean;
import org.nanotek.entities.BaseReleaseCommentBean;
import org.nanotek.entities.BaseReleaseGroupBean;
import org.nanotek.entities.BaseReleasePackagingBean;
import org.nanotek.entities.BaseReleaseStatusBean;
import org.nanotek.proxy.ProxyBase;

/**
 * 
 * @author josecanova
 *
 */
public class ReleaseBean 
<K extends BaseBean<ReleaseBean<K>,Release<?>>> 
extends ProxyBase<ReleaseBean<K>,Release<?>>
implements BaseReleaseBean<ReleaseBean<K>>{

	private static final long serialVersionUID = -11618084576388817L;
	
	BaseArtistCreditBean<?> artistCredit;
	
	BaseReleaseGroupBean<?> releaseGroup;
	
	BaseReleaseCommentBean<?> releaseComment;
	
	BaseReleasePackagingBean<?> releasePackaging;
	
	BaseLanguageBean<?> language;
	
	BaseReleaseBarCodeBean<?> releaseBarCode;
	
	BaseReleaseStatusBean<?> releaseStatus;
	
	
	
	
//	public Long releaseId; 
//	public String gid; 
//	public String name;
//	public Long artistCreditId; 
//	public Long releaseGroup; 
//	public Long status; 
//	public Long  packaging; 
//	public Long  language; 
//	public Integer script;
//	public String barcode; 
//	public String comment; 
//	public Integer editsPending; 
//	public Integer quality; 
//	public String lastUpdated;
//	
	public ReleaseBean(Class<? extends Release<?>> class1) {
		super(class1);
		postContruct();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends Release<?>> castClass() {
		return (Class<? extends Release<?>>) 
				Release.class.asSubclass(Release.class);
	}
	
	public ReleaseBean() {
		super(castClass());
		postContruct();
	}
	

	private void postContruct() {
		 artistCredit = new ArtistCreditBean<>();
		 releaseGroup = new ReleaseGroupBean<>();
		 releaseComment = new ReleaseCommentBean<>(); 
		 releasePackaging = new ReleasePackagingBean<> ();
		 language = new LanguageBean<>();
		 releaseBarCode = new ReleaseBarCodeBean<> ();
		 releaseStatus = new ReleaseStatusBean<> ();
	}


	public BaseArtistCreditBean<?> getArtistCredit() {
		return artistCredit;
	}


	public void setArtistCredit(BaseArtistCreditBean<?> artistCredit) {
		this.artistCredit = artistCredit;
	}


	public BaseReleaseGroupBean<?> getReleaseGroup() {
		return releaseGroup;
	}


	public void setReleaseGroup(BaseReleaseGroupBean<?> releaseGroup) {
		this.releaseGroup = releaseGroup;
	}


	public BaseReleaseCommentBean<?> getReleaseComment() {
		return releaseComment;
	}


	public void setReleaseComment(BaseReleaseCommentBean<?> releaseComment) {
		this.releaseComment = releaseComment;
	}


	public BaseReleasePackagingBean<?> getReleasePackaging() {
		return releasePackaging;
	}


	public void setReleasePackaging(BaseReleasePackagingBean<?> releasePackaging) {
		this.releasePackaging = releasePackaging;
	}


	public BaseLanguageBean<?> getLanguage() {
		return language;
	}


	public void setLanguage(BaseLanguageBean<?> language) {
		this.language = language;
	}


	public BaseReleaseBarCodeBean<?> getReleaseBarCode() {
		return releaseBarCode;
	}


	public void setReleaseBarCode(BaseReleaseBarCodeBean<?> releaseBarCode) {
		this.releaseBarCode = releaseBarCode;
	}


	public BaseReleaseStatusBean<?> getReleaseStatus() {
		return releaseStatus;
	}


	public void setReleaseStatus(BaseReleaseStatusBean<?> releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
	
	
	
}
