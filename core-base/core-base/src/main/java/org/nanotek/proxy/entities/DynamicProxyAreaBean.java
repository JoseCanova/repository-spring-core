package org.nanotek.proxy.entities;

import java.util.UUID;

import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.MutableAreaBeginDateEntity;
import org.nanotek.entities.MutableAreaCommentEntity;
import org.nanotek.entities.MutableAreaEndDateEntity;
import org.nanotek.entities.MutableAreaIdEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableTypeEntity;

public interface DynamicProxyAreaBean<T> 
extends 
MutableAreaIdEntity<Long>,		
MutableTypeEntity<AreaType<?>>,
MutableAreaCommentEntity<AreaComment<?>>,
MutableAreaBeginDateEntity<AreaBeginDate<?>>,
MutableAreaEndDateEntity<AreaEndDate<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>{

	@Override
	default AreaBeginDate<?> getAreaBeginDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default AreaComment<?> getAreaComment() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default AreaEndDate<?> getAreaEndDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default Long getAreaId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default UUID getGid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default AreaType<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default void setAreaBeginDate(AreaBeginDate<?> k) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void setAreaComment(AreaComment<?> k) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void setAreaEndDate(AreaEndDate<?> k) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void setAreaId(Long k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void setType(AreaType<?> k) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void setGid(UUID k) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void setName(String k) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
}
