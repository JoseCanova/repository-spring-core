package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.entities.BaseAreaBeginDateBean;
import org.nanotek.entities.BaseAreaCommentBean;
import org.nanotek.entities.BaseAreaEndDateBean;
import org.nanotek.entities.BaseAreaTypeBean;
import org.nanotek.proxy.ProxyBase;


public class AreaBean
<K extends BaseBean<AreaBean<K>,Area<?>>> 
extends ProxyBase<AreaBean<K>,Area<?>>
implements BaseAreaBean<AreaBean<K>>{

	private static final long serialVersionUID = 1708381486272333902L;
	
	private BaseAreaBeginDateBean<?> areaBeginDate;
	
	private BaseAreaEndDateBean<?> areaEndDate;
	
	private BaseAreaCommentBean<?> areaComment;
	
	private BaseAreaTypeBean<?> areaType;

	public AreaBean() {
		super(castClass());
		postConstruct();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends Area<?>> castClass() {
		return (Class<? extends Area<?>>) 
				Area.class.asSubclass(Area.class);
	}
	
	public AreaBean(Class<Area<?>>id) {
		super(id);
		postConstruct();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void postConstruct() {
		areaBeginDate = new  AreaBeginDateBean(AreaBeginDate.class); 
		areaEndDate = new AreaEndDateBean(AreaEndDate.class);
		areaComment = new AreaCommentBean(AreaComment.class);
		areaType = new AreaTypeBean(AreaType.class);
	}


	public BaseAreaBeginDateBean<?> getAreaBeginDate() {
		return areaBeginDate;
	}


	public void setAreaBeginDate(BaseAreaBeginDateBean<?> areaBeginDate) {
		this.areaBeginDate = areaBeginDate;
	}
	

	public BaseAreaEndDateBean<?> getAreaEndDate() {
		return areaEndDate;
	}


	public void setAreaEndDate(BaseAreaEndDateBean<?> areaEndDate) {
		this.areaEndDate = areaEndDate;
	}
	

	public BaseAreaCommentBean<?> getAreaComment() {
		return areaComment;
	}

	public void setAreaComment(BaseAreaCommentBean<?> areaComment) {
		this.areaComment = areaComment;
	}

	
	public BaseAreaTypeBean<?> getAreaType() {
		return areaType;
	}


	public void setAreaType(BaseAreaTypeBean<?> areaType) {
		this.areaType = areaType;
	}
	
	
	public BaseAreaTypeBean<?> getType() {
		return areaType;
	}


	public void setType(BaseAreaTypeBean<?> areaType) {
		this.areaType = areaType;
	}
	
	
	public static void main(String[] args) {
		AreaBean a = new AreaBean(Area.class);
		a.setComment("This is a comment");
		a.setAreaId(1000L);
		a.setBeginYear(100);
		a.setBeginDay(100);
		a.getBeginDay();
		System.out.println(a.getComment() + "  " + a.getAreaId());
	}


}
