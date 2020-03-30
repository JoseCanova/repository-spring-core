package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.SortNameEntity;
import org.nanotek.beans.csv.ArtistAliasSortNameBean;
import org.nanotek.beans.entity.ArtistAliasSortName;

public interface BaseArtistAliasSortNameBean
<K extends BaseBean<K,ArtistAliasSortName<?>>> 
extends 
Base<K>,
BaseBean<K,ArtistAliasSortName<?>>,
MutableSortNameEntity<String>{

	@Override
	default String getSortName() {
		return read(SortNameEntity.class).map(s ->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setSortName(String k) {
		write(MutableSortNameEntity.class,k);
	}
	
	
	public static void main(String[] args) { 
		ArtistAliasSortNameBean bean = new ArtistAliasSortNameBean();
		bean.setSortName("a  sort name");
		System.out.println(bean.getSortName());
	}
}
