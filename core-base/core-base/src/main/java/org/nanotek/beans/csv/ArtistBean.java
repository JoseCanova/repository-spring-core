package org.nanotek.beans.csv;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.entities.BaseArtistBean;
import org.nanotek.entities.MutableAreaEntity;
import org.nanotek.entities.MutableArtistBeginAreaEntity;
import org.nanotek.entities.MutableArtistEndAreaEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.MutableBeginDateDayEntity;
import org.nanotek.entities.MutableBeginDateMonthEntity;
import org.nanotek.entities.MutableBeginDateYearEntity;
import org.nanotek.entities.MutableCommentEntity;
import org.nanotek.entities.MutableEndDateDayEntity;
import org.nanotek.entities.MutableEndDateMonthEntity;
import org.nanotek.entities.MutableEndDateYearEntity;
import org.nanotek.entities.MutableGenderEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableTypeEntity;
import org.nanotek.opencsv.CsvBaseBean;

import com.sun.xml.bind.v2.model.core.ID;


/*
 * public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>
 * 
 */


public class ArtistBean
<K extends BaseBean<K,Artist<?>>> 
extends CsvBaseBean<Artist<?>>
implements BaseArtistBean<K>
{


	private static final long serialVersionUID = 2864330060600897052L;


	@NotNull
	private Long artistId;
	@NotNull
	private String gid;
	@NotNull
	private String name;
	@NotNull
	private String sortName;

	private Integer beginDateYear;
	private Integer beginDateMonth;
	private Integer beginDateDay;
	private Integer endDateYear;
	private Integer endDateMonth;
	private Integer endDateDay;  
	private Long typeId;
	private Long areaId;
	private Long genderId;
	private String comment;
	private Long editsPending;
	private String lastUpdated;
	private String ended;
	private Long beginArea;
	private Long endArea;
	@Override
	public int compareTo(K o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public  ArtistBean() {
		super((Class<Artist<?>>)Artist.class.asSubclass(Artist.class));
	}



	public ArtistBean(Class<Artist<?>>id) {
		super(id);
	}



	public static void main(String[] args) { 
		ArtistBean abean = new ArtistBean(Artist.class);
		abean.setBeginDateYear(299);
		abean.setArtistId(1000L);
		abean.setName("1000L");
		
		
		
		System.out.println(" artist Name" + abean.getName());
		System.out.println(" artist Year" + abean.getBeginDateYear());
		System.out.println(" artist id" + abean.getArtistId());
	}


	
	
}
