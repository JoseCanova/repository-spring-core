package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistAliasSortNameBean;
import org.nanotek.beans.entity.ReleaseAliasSortName;
import org.nanotek.entities.immutables.SortNameEntity;

public interface BaseReleaseAliasSortNameBean
<K extends BaseBean<K,ReleaseAliasSortName<?>>> 
extends 
Base<K>,
BaseBean<K,ReleaseAliasSortName<?>>,
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
