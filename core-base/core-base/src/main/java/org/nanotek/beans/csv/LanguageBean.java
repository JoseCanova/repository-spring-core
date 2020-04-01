package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.beans.entity.Language;
import org.nanotek.entities.BaseInstrumentTypeBean;
import org.nanotek.entities.BaseLanguageBean;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.proxy.ProxyBase;

public class LanguageBean
<K extends BaseBean<LanguageBean<K>,Language<?>>> 
extends ProxyBase<LanguageBean<K>,Language<?>>
implements BaseLanguageBean<LanguageBean<K>> {


	public LanguageBean() {
		super((Class<? extends Language<?>>) Language.class);
	}
	
	public LanguageBean(Class<? extends Language<?>> class1) {
		super(class1);
	}

	private static final long serialVersionUID = 2997501833949969600L;


//	
//	public Long laguageId; 
//	public String isoCode2t; 
//	public String isoCode2b; 
//	public String isoCode1; 
//	public String name; 
//	public Long frequency;
//	public String isoCode3;
//	
	

}
