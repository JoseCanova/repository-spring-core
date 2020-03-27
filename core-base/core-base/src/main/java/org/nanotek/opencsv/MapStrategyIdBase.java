package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.BaseBean;
import org.nanotek.beans.csv.AreaBean;


public class MapStrategyIdBase <K extends WrappedBaseClass<J>, J extends BaseBean<?,?>> implements MapStrategy <K,J>{
	
	Optional<J> optionalIdBaseMap;
	
	Optional<K> optionalWrappedBaseClass;
//	
//	public static void main(String[] args) {
//		Optional<?> strategy = new MapStrategyIdBase().createWrappedBaseClass(AreaBean.class);
//		
//	}
	


}
