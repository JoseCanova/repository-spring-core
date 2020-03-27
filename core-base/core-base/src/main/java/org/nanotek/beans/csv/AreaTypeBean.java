package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.BaseAreaTypeBean;
import org.nanotek.opencsv.CsvBaseBean; 

//public interface BaseAreaTypeBean<K extends BaseBean<K,AreaType<?>>> 
//extends Base<K>,
//BaseBean<K,AreaType<?>>
//<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
//extends CsvBaseBean<ID>
//implements BaseAreaTypeBean<K>


public class AreaTypeBean
<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
extends CsvBaseBean<ID>
implements BaseAreaTypeBean<K> {

	private static final long serialVersionUID = -6271568961378072618L;

	public Long typeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;


	public AreaTypeBean() {
		super((Class<? extends ID>) AreaType.class.asSubclass(AreaType.class));
	}

	

	public AreaTypeBean(Class<? extends ID> class1) {
		super(class1);
	}



	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public static void main(String[] args) {
		AreaTypeBean<?,? super AreaType<?>> a = new AreaTypeBean<>(AreaType.class);
	}
	
	
}
