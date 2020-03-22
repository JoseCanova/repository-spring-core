package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.csv.BaseBean;


public class MapStrategyIdBase <K extends WrappedBaseClass<J>, J extends BaseBean<?,?>> implements MapStrategy <K,J>{
	
	Optional<J> optionalIdBaseMap;
	
	Optional<K> optionalWrappedBaseClass;
	
	public static void main(String[] args) {
		new MapStrategyIdBase().createWrappedBaseClass(AreaBean.class);
	}
	


}
