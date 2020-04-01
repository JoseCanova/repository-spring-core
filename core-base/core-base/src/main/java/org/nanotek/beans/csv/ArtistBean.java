package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.entities.BaseArtistBean;
import org.nanotek.entities.BaseArtistBeginDateBean;
import org.nanotek.entities.BaseArtistCommentBean;
import org.nanotek.entities.BaseArtistEndDateBean;
import org.nanotek.entities.BaseArtistSortNameBean;
import org.nanotek.entities.BaseArtistTypeBean;
import org.nanotek.entities.BaseGenderBean;
import org.nanotek.proxy.ProxyBase;


/*
 * public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>
 * 
 */
public class ArtistBean
<K extends BaseBean<ArtistBean<K>,Artist<?>>> 
extends ProxyBase<ArtistBean<K>,Artist<?>>
implements BaseArtistBean<ArtistBean<K>>
{


	private static final long serialVersionUID = 2864330060600897052L;
	
	
	BaseArtistSortNameBean<?> artistSortName;
	
	BaseArtistCommentBean<?> artistComment;

	BaseArtistBeginDateBean<?> artistBeginDate;
	
	BaseArtistEndDateBean<?> artistEndDate;
	
	BaseArtistTypeBean<?> artistType;
	
	BaseGenderBean<?> gender;
	
	BaseAreaBean<?> area;
	
	BaseAreaBean<?> beginArea;
	
	BaseAreaBean<?> endArea;
		
//	@NotNull
//	private Long artistId;
//	@NotNull
//	private String gid;
//	@NotNull
//	private String name;
//	@NotNull
//	private String sortName;
//
//	private Integer beginDateYear;
//	private Integer beginDateMonth;
//	private Integer beginDateDay;
//	private Integer endDateYear;
//	private Integer endDateMonth;
//	private Integer endDateDay;  
//	private Long typeId;
//	private Long areaId;
//	private Long genderId;
//	private String comment;
//	private Long editsPending;
//	private String lastUpdated;
//	private String ended;
//	private Long beginArea;
//	private Long endArea;
	
	
	
	public  ArtistBean() {
		super( castClass());
		postConstruct();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Artist<?>> castClass() {
		return (Class<? extends Artist<?>>) 
				Artist.class.
				asSubclass(Artist.class);
	}

	private void postConstruct() {
		 artistSortName = new ArtistSortNameBean<>();
		
		artistComment = new ArtistCommentBean<> ();

		artistBeginDate = new ArtistBeginDateBean<> ();
		
		artistEndDate = new ArtistEndDateBean<>();
		
		BaseArtistTypeBean<?> artistType = new ArtistTypeBean<> ();
		
		BaseGenderBean<?> gender = new GenderBean<>();
		
		 area = new AreaBean<>();
		
		 beginArea = new  AreaBean<>();
		
		endArea = new AreaBean<> ();
	}



	public ArtistBean(Class<Artist<?>>id) {
		super(id);
		postConstruct();
	}



	public BaseArtistSortNameBean<?> getArtistSortName() {
		return artistSortName;
	}



	public void setArtistSortName(BaseArtistSortNameBean<?> artistSortName) {
		this.artistSortName = artistSortName;
	}




	public BaseArtistBeginDateBean<?> getArtistBeginDate() {
		return artistBeginDate;
	}



	public void setArtistBeginDate(BaseArtistBeginDateBean<?> artistBeginDate) {
		this.artistBeginDate = artistBeginDate;
	}



	public BaseArtistEndDateBean<?> getArtistEndDate() {
		return artistEndDate;
	}



	public void setArtistEndDate(BaseArtistEndDateBean<?> artistEndDate) {
		this.artistEndDate = artistEndDate;
	}



	public BaseArtistTypeBean<?> getArtistType() {
		return artistType;
	}



	public void setArtistType(BaseArtistTypeBean<?> artistType) {
		this.artistType = artistType;
	}



	public BaseGenderBean<?> getGender() {
		return gender;
	}



	public void setGender(BaseGenderBean<?> gender) {
		this.gender = gender;
	}



	public BaseAreaBean<?> getArea() {
		return area;
	}



	public void setArea(BaseAreaBean<?> area) {
		this.area = area;
	}



	public BaseAreaBean<?> getBeginArea() {
		return beginArea;
	}



	public void setBeginArea(BaseAreaBean<?> beginArea) {
		this.beginArea = beginArea;
	}



	public BaseAreaBean<?> getEndArea() {
		return endArea;
	}



	public void setEndArea(BaseAreaBean<?> endArea) {
		this.endArea = endArea;
	}



	public BaseArtistCommentBean<?> getArtistComment() {
		return artistComment;
	}



	public void setArtistComment(BaseArtistCommentBean<?> artistComment) {
		this.artistComment = artistComment;
	}

}
