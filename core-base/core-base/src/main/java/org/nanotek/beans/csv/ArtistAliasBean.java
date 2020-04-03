package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.entities.BaseArtistAliasBean;
import org.nanotek.entities.BaseArtistAliasBeginDateBean;
import org.nanotek.entities.BaseArtistAliasEndDateBean;
import org.nanotek.entities.BaseArtistAliasLocaleBean;
import org.nanotek.entities.BaseArtistAliasSortNameBean;
import org.nanotek.entities.BaseArtistAliasTypeBean;
import org.nanotek.entities.BaseArtistBean;
import org.nanotek.opencsv.ArtistAliasEndDateBean;
import org.nanotek.proxy.ProxyBase;

//public interface BaseAreaTypeBean<K extends BaseBean<K,AreaType<?>>> 
//extends Base<K>,
//BaseBean<K,AreaType<?>>
//<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
//extends CsvBaseBean<ID>
//implements BaseAreaTypeBean<K>
public class ArtistAliasBean
<K extends BaseBean<ArtistAliasBean<K>,ArtistAlias<?>>> 
extends ProxyBase<ArtistAliasBean<K>,ArtistAlias<?>>
implements BaseArtistAliasBean<ArtistAliasBean<K>>{

	private static final long serialVersionUID = -2745888243978330408L;

	BaseArtistAliasTypeBean<?> artistAliasType;

	BaseArtistAliasLocaleBean<?> artistAliasLocale;

	BaseArtistAliasBeginDateBean<?> artistAliasBeginDate;

	BaseArtistAliasEndDateBean<?> artistAliasEndDate;

	BaseArtistAliasSortNameBean<?> artistAliasSortName;

	BaseArtistBean<?> artist;


	public ArtistAliasBean() {
		super(castClass());
		postConstruct();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistAlias<?>> castClass() {
		return (Class<? extends ArtistAlias<?>>) ArtistAlias.class.asSubclass(ArtistAlias.class);
	}

	private void postConstruct() {
		artist = new ArtistBean<>();
		artistAliasType = new ArtistAliasTypeBean<>();

		artistAliasLocale = new ArtistAliasLocaleBean<>();

		artistAliasBeginDate = new ArtistAliasBeginDateBean<> ();

		artistAliasEndDate = new ArtistAliasEndDateBean<>();

		artistAliasSortName = new ArtistAliasSortNameBean<>();		
	}

	public ArtistAliasBean(Class<? extends ArtistAlias<?>> class1) {
		super(class1);
		postConstruct();
	}



	public BaseArtistAliasTypeBean<?> getArtistAliasType() {
		return artistAliasType;
	}

	public void setArtistAliasType(BaseArtistAliasTypeBean<?> artistAliasType) {
		this.artistAliasType = artistAliasType;
	}

	public BaseArtistAliasLocaleBean<?> getArtistAliasLocale() {
		return artistAliasLocale;
	}

	public void setArtistAliasLocale(BaseArtistAliasLocaleBean<?> artistAliasLocale) {
		this.artistAliasLocale = artistAliasLocale;
	}

	public BaseArtistAliasBeginDateBean<?> getArtistAliasBeginDate() {
		return artistAliasBeginDate;
	}

	public void setArtistAliasBeginDate(BaseArtistAliasBeginDateBean<?> artistAliasBeginDate) {
		this.artistAliasBeginDate = artistAliasBeginDate;
	}

	public BaseArtistAliasEndDateBean<?> getArtistAliasEndDate() {
		return artistAliasEndDate;
	}

	public void setArtistAliasEndDate(BaseArtistAliasEndDateBean<?> artistAliasEndDate) {
		this.artistAliasEndDate = artistAliasEndDate;
	}

	public BaseArtistAliasSortNameBean<?> getArtistAliasSortName() {
		return artistAliasSortName;
	}

	public void setArtistAliasSortName(BaseArtistAliasSortNameBean<?> artistAliasSortName) {
		this.artistAliasSortName = artistAliasSortName;
	}

	public BaseArtistBean<?> getArtist() {
		return artist;
	}

	public void setArtist(BaseArtistBean<?> artist) {
		this.artist = artist;
	}



	//	
	//	public Long aliasId; 
	//	public Long artistId; 
	//	public String name; 
	//	public Long artistAliasLocale; 
	//	public Integer editsPending;
	//	public String lastUpdated; 
	//	public Long artistAliasType; 
	//	public String artistAliasSortName;
	//	public Integer beginYear; 
	//	public Integer beginMonth;
	//	public Integer beginDay;
	//	public Integer endDateYear; 
	//	public Integer endDateMonth;
	//	public Integer endDateDay;
	//	public String primaryForLocale;
	//	public String ended;


	
	public static void main(String[] args) {
		ArtistAliasBean bean = new ArtistAliasBean();
	}

}
