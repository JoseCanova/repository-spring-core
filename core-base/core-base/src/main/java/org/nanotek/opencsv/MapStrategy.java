package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.BaseBean;
import org.nanotek.IdBase;
import org.nanotek.WrappedEntityBase;

public interface MapStrategy
<K extends WrappedBaseClass<J>, 
J extends BaseBean<?,?>>  
{


//	default Optional <?>  createWrappedBaseClass(Class<J> clazz){ 
//		Optional<J> jon = BaseBean.newBaseBeanInstance(clazz);
//		J instance = jon.get();
//		Optional <?> k = WrappedEntityBase.newBaseBeanInstance(jon.getClass().asSubclass(clazz),instance);
//		return k;
//	}
	
}
