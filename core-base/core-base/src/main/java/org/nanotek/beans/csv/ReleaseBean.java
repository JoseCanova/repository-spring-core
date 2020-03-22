package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Release;

/**
 * 
 * @author josecanova
 *
 */
public class ReleaseBean 
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	
	
	
	private static final long serialVersionUID = -11618084576388817L;
	
	private ID id; 
	
	public ID getId() { 
		return id;
	}
	
	public Long releaseId; 
	public String gid; 
	public String name;
	public Long artistCreditId; 
	public Long releaseGroup; 
	public Long status; 
	public Long  packaging; 
	public Long  language; 
	public Integer script;
	public String barcode; 
	public String comment; 
	public Integer editsPending; 
	public Integer quality; 
	public String lastUpdated;
	
	
	public ReleaseBean(Class<ID> id) {
		super(id);
	}

	public ReleaseBean() {
		super(Release.class);
	}
	
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getArtistCreditId() {
		return artistCreditId;
	}
	public void setArtistCreditId(Long artistCreditId) {
		this.artistCreditId = artistCreditId;
	}
	public Long getReleaseGroup() {
		return releaseGroup;
	}
	public void setReleaseGroup(Long releaseGroup) {
		this.releaseGroup = releaseGroup;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Integer getScript() {
		return script;
	}
	public void setScript(Integer script) {
		this.script = script;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getEditsPending() {
		return editsPending;
	}
	public void setEditsPending(Integer editsPending) {
		this.editsPending = editsPending;
	}
	public Integer getQuality() {
		return quality;
	}
	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Long getPackaging() {
		return packaging;
	}

	public void setPackaging(Long packaging) {
		this.packaging = packaging;
	}

	public Long getLanguage() {
		return language;
	}

	public void setLanguage(Long language) {
		this.language = language;
	}


	public Long getReleaseId() {
		return releaseId;
	}


	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	} 
	
}
