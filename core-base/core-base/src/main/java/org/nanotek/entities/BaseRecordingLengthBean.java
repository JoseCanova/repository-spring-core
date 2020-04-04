package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.RecordingLengthBean;
import org.nanotek.beans.entity.RecordingLength;
import org.nanotek.entities.immutables.LengthtEntity;

public interface BaseRecordingLengthBean
<K extends BaseBean<K,RecordingLength<?>>>
extends
Base<K>,
BaseBean<K,RecordingLength<?>>,
MutableLengthEntity<Long>
{
     @Override
    default Long getLength() {
    	return read(LengthtEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
    }
     
    @Override
    default void setLength(Long k) {
    	write(MutableLengthEntity.class,k);
    } 
    
    public static void main(String[] args) {
    	RecordingLengthBean bean = new RecordingLengthBean();
    	bean.setLength(1000l);
    	System.out.println(bean.getLength());
    }
}
