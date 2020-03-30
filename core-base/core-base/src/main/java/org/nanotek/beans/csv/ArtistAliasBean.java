package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.nanotek.beans.entity.ArtistAliasEndDate;
import org.nanotek.beans.entity.ArtistAliasLocale;
import org.nanotek.beans.entity.ArtistAliasSortName;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.entities.BaseArtistAliasBean;
import org.nanotek.entities.MutableAliasIdEntity;
import org.nanotek.entities.MutableArtistAliasBeginDateEntity;
import org.nanotek.entities.MutableArtistAliasEndDateEntity;
import org.nanotek.entities.MutableArtistAliasLocaleEntity;
import org.nanotek.entities.MutableArtistAliasSortNameEntity;
import org.nanotek.entities.MutableArtistAliasTypeEntity;
import org.nanotek.entities.MutableArtistEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.MutableBeginDayEntity;
import org.nanotek.entities.MutableBeginMonthEntity;
import org.nanotek.entities.MutableBeginYearEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.proxy.ProxyBase;

//public interface BaseAreaTypeBean<K extends BaseBean<K,AreaType<?>>> 
//extends Base<K>,
//BaseBean<K,AreaType<?>>
//<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
//extends CsvBaseBean<ID>
//implements BaseAreaTypeBean<K>
public class ArtistAliasBean
<ID extends ArtistAlias<?>, K extends BaseBean<K,ArtistAlias<?>>> 
extends ProxyBase<ID>
implements BaseArtistAliasBean<K>{

	private static final long serialVersionUID = -2745888243978330408L;
	
	private ID id; 
	
	@Override
	public ID getId(){ 
		return id;
	}
	
	public Long aliasId; 
	public Long artistId; 
	public String name; 
	public Long artistAliasLocale; 
	public Integer editsPending;
	public String lastUpdated; 
	public Long artistAliasType; 
	public String artistAliasSortName;
	public Integer beginYear; 
	public Integer beginMonth;
	public Integer beginDay;
	public Integer endDateYear; 
	public Integer endDateMonth;
	public Integer endDateDay;
	public String primaryForLocale;
	public String ended;
	
	
	public ArtistAliasBean() {
		super(null);
	}

	public ArtistAliasBean(Class<ID> id) {
		super(id);
	}

	public Long getArtistId() {
		return artistId;
	}


	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getEditsPending() {
		return editsPending;
	}


	public void setEditsPending(Integer editsPending) {
		this.editsPending = editsPending;
	}


	public String getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public Integer getEndDateYear() {
		return endDateYear;
	}


	public void setEndDateYear(Integer endDateYear) {
		this.endDateYear = endDateYear;
	}


	public Integer getEndDateMonth() {
		return endDateMonth;
	}


	public void setEndDateMonth(Integer endDateMonth) {
		this.endDateMonth = endDateMonth;
	}


	public Integer getEndDateDay() {
		return endDateDay;
	}


	public void setEndDateDay(Integer endDateDay) {
		this.endDateDay = endDateDay;
	}


	public String getPrimaryForLocale() {
		return primaryForLocale;
	}


	public void setPrimaryForLocale(String primaryForLocale) {
		this.primaryForLocale = primaryForLocale;
	}


	public String getEnded() {
		return ended;
	}


	public void setEnded(String ended) {
		this.ended = ended;
	}


	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

	public Long getAliasId() {
		return aliasId;
	}

	public void setAliasId(Long aliasId) {
		this.aliasId = aliasId;
	}

	public String getArtistAliasSortName() {
		return artistAliasSortName;
	}

	public void setArtistAliasSortName(String artistAliasSortName) {
		this.artistAliasSortName = artistAliasSortName;
	}

	public Long getArtistAliasLocale() {
		return artistAliasLocale;
	}

	public void setArtistAliasLocale(Long artistAliasLocale) {
		this.artistAliasLocale = artistAliasLocale;
	}

	public Long getArtistAliasType() {
		return artistAliasType;
	}

	public void setArtistAliasType(Long artistAliasType) {
		this.artistAliasType = artistAliasType;
	}

	public Integer getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(Integer beginYear) {
		this.beginYear = beginYear;
	}

	public Integer getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(Integer beginMonth) {
		this.beginMonth = beginMonth;
	}

	public Integer getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Integer beginDay) {
		this.beginDay = beginDay;
	}
	
}
