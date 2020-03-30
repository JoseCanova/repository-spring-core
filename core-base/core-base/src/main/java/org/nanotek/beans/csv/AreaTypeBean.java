package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.BaseAreaTypeBean;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.opencsv.CsvBaseBean; 

//public interface BaseAreaTypeBean<K extends BaseBean<K,AreaType<?>>> 
//extends Base<K>,
//BaseBean<K,AreaType<?>>
//<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
//extends CsvBaseBean<ID>
//implements BaseAreaTypeBean<K>


public class AreaTypeBean
<K extends BaseBean<AreaTypeBean<K>,AreaType<?>>> 
extends CsvBaseBean<AreaTypeBean<K>,AreaType<?>>
implements BaseAreaTypeBean<AreaTypeBean<K>> {

	private static final long serialVersionUID = -6271568961378072618L;

	BaseBaseTypeDescriptionBean<?> baseTypeDescription;
	
	public <ID extends AreaType<?>> AreaTypeBean() {
		super((Class<? extends AreaType<?>>) AreaType.class);
		postConstruct();
	}
	
	private void postConstruct() {
		baseTypeDescription = new  BaseTypeDescriptionBean();
	}

	public AreaTypeBean(Class<AreaType<?>> class1) {
		super(class1);
		postConstruct();
	}

	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}

	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}
	
}
