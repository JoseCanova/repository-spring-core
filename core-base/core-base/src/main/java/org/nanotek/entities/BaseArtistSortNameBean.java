package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.SortNameEntity;
import org.nanotek.beans.csv.ArtistSortNameBean;
import org.nanotek.beans.entity.ArtistSortName;

public interface BaseArtistSortNameBean
<K extends  BaseBean<K,ArtistSortName<?>>> 
extends 
Base<K>,
BaseBean<K,ArtistSortName<?>>,
MutableSortNameEntity<String>
{
     @Override
    default String getSortName() {
    	return read(SortNameEntity.class).map(s->String.class.cast(s)).orElse("");
    }
     
    @Override
    default void setSortName(String k) {
    	write(MutableSortNameEntity.class,k);
    } 
    
    public static void main(String[] args) {
    	ArtistSortNameBean bean = new ArtistSortNameBean();
    	bean.setSortName("this is a sort name");
    	System.out.println(bean.getSortName());
    }
}
