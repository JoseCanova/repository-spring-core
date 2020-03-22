package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.IdBase;
import org.nanotek.WrappedEntityBase;
import org.nanotek.beans.csv.BaseBean;

public interface MapStrategy
<K extends WrappedBaseClass<J>, 
J extends BaseBean<?,?>>  
{


	default Optional <?>  createWrappedBaseClass(Class<J> clazz){ 
		Optional<J> jon = BaseBean.newBaseBeanInstance(clazz);
		J instance = jon.get();
		Optional <?> k = WrappedEntityBase.newBaseBeanInstance(jon.getClass().asSubclass(IdBase.class),instance);
		return k;
	}
	
}
