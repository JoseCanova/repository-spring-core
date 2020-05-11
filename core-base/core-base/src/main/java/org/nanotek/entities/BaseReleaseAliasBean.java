package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseAliasBean;
import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.entities.immutables.ReleaseAliasIdEntity;
import org.nanotek.entities.immutables.ReleaseAliasNameEntity;

public interface BaseReleaseAliasBean
<K extends BaseBean<K,ReleaseAlias<?>>> 
extends Base<K>,
BaseBean<K,ReleaseAlias<?>>,
MutableReleaseAliasIdEntity<Long>,
MutableReleaseAliasLocaleEntity<BaseReleaseAliasLocaleBean<?>>,
MutableReleaseAliasSortNameEntity<BaseReleaseAliasSortNameBean<?>>,
MutableReleaseEntity<BaseReleaseBean<?>>,
MutableReleaseAliasTypeEntity<BaseReleaseAliasTypeBean<?>>,
MutableReleaseAliasBeginDateEntity<BaseReleaseAliasBeginDateBean<?>>,
MutableReleaseAliasEndDateEntity<BaseReleaseAliasEndDateBean<?>>,
MutableReleaseAliasNameEntity<String>
{

	
//	public Long releaseAliasId; 
//	public Long releaseId; 
//	public String releaseAliasName; 
//	public String localeId; 
//	public Integer editsPending; 
//	public String lastUpdated; 
//	public Long typeId; 
//	public String releaseAliasSortName;
//	public Integer beginDateYear ;
//	public Integer beginDateMonth;
//	public Integer beginDateDay;
//	public Integer endDateYear;
//	public Integer endDateMonth;
//	public Integer endDateDay; 
//	public String primaryForLocale;
//	public String ended;
	
	@Override
	default Long getReleaseAliasId() {
		return read(ReleaseAliasIdEntity.class).map(s->Long.class.cast(s)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setReleaseAliasId(Long k) {
		write(MutableReleaseAliasIdEntity.class,k);
	}
	
	@Override
	default void setReleaseAliasName(String k) {
		write(MutableReleaseAliasNameEntity.class,k);
	}
	
	default String getReleaseAliasName() { 
		return read (ReleaseAliasNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	default void setBeginYear(Integer k) { 
		getReleaseAliasBeginDate().setBeginYear(k);
	}
	
	default Integer getBeginYear() { 
		return getReleaseAliasBeginDate().getBeginYear();
	}
	
	default void setBeginMonth(Integer k) { 
		getReleaseAliasBeginDate().setBeginMonth(k);
	}
	
	default Integer getBeginMonth() { 
		return getReleaseAliasBeginDate().getBeginMonth();
	}
	
	default void setBeginDay(Integer k) { 
		getReleaseAliasBeginDate().setBeginDay(k);
	}
	
	default Integer getBeginDay() { 
		return getReleaseAliasBeginDate().getBeginDay();
	}
	
	default void setEndYear(Integer k) { 
		getReleaseAliasEndDate().setEndYear(k);
	}
	
	default Integer getEndYear() { 
		return getReleaseAliasEndDate().getEndYear();
	}
	
	default void setEndMonth(Integer k) { 
		getReleaseAliasEndDate().setEndMonth(k);
	}
	
	default Integer getEndMonth() { 
		return getReleaseAliasEndDate().getEndMonth();
	}
	
	default void setEndDay(Integer k) { 
		getReleaseAliasEndDate().setEndDay(k);
	}
	
	default Integer getEndDay() { 
		return getReleaseAliasEndDate().getEndDay();
	}
	
	default void setSortName(String s) { 
		getReleaseAliasSortName().setSortName(s);
	}
	
	default String getSortName() { 
		return getReleaseAliasSortName().getSortName();
	}
	
	
	default void setTypeId(Long id) { 
		Long theId = id==null?2l:id;
		getReleaseAliasType().setTypeId(theId);
	}
	
	default Long getTypeId() { 
		return getReleaseAliasType().getTypeId();
	}
	
	default void setLocale(String s) { 
		getReleaseAliasLocale().setLocale(s);
	}
	
	default String getLocale() { 
		return getReleaseAliasLocale().getLocale();
	}
	
	default void setReleaseId(Long id) { 
		getRelease().setReleaseId(id);
	}
	
	default Long getReleaseId() { 
		return getRelease().getReleaseId();
	}
	
	public static void main(String[] args) { 
		ReleaseAliasBean<?> bean = new ReleaseAliasBean<>();
	}
}
