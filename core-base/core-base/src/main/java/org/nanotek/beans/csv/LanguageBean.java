package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Language;

public class LanguageBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	private static final long serialVersionUID = 2997501833949969600L;

	private ID id;
	
	public ID getId() { 
		return id;
	}
	
	public Long laguageId; 
	public String isoCode2t; 
	public String isoCode2b; 
	public String isoCode1; 
	public String name; 
	public Long frequency;
	public String isoCode3;
	
	
	public LanguageBean(Class<ID> id) {
		super(id);
	}

	public LanguageBean() {
		super(Language.class);
	}
	

	public String getIsoCode2t() {
		return isoCode2t;
	}


	public void setIsoCode2t(String isoCode2t) {
		this.isoCode2t = isoCode2t;
	}


	public String getIsoCode2b() {
		return isoCode2b;
	}


	public void setIsoCode2b(String isoCode2b) {
		this.isoCode2b = isoCode2b;
	}


	public String getIsoCode1() {
		return isoCode1;
	}


	public void setIsoCode1(String isoCode1) {
		this.isoCode1 = isoCode1;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getFrequency() {
		return frequency;
	}


	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}


	public String getIsoCode3() {
		return isoCode3;
	}


	public void setIsoCode3(String isoCode3) {
		this.isoCode3 = isoCode3;
	}




	public Long getLaguageId() {
		return laguageId;
	}




	public void setLaguageId(Long laguageId) {
		this.laguageId = laguageId;
	}


}
