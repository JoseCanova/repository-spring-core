package org.nanotek.proxy.entities;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.entities.BaseAreaBeginDateBean;
import org.nanotek.entities.BaseAreaCommentBean;
import org.nanotek.entities.BaseAreaEndDateBean;
import org.nanotek.proxy.ProxyBase;


public class ProxyAreaBean
<K extends BaseBean<ProxyAreaBean<K>,Area<?>>>
implements DynamicProxyAreaBean<ProxyAreaBean<K>>
,ProxyBaseBean<ProxyAreaBean<K>,Area<?>>{

	private static final long serialVersionUID = 1708381486272333902L;
	
	public Long areaId; 
	public UUID gid;
	public AreaType<?> areaType; 
	public AreaBeginDate<?> areaBeginDate; 
	public AreaEndDate<?> areaEndDate;
	private AreaComment<?> areaComment;
	
	Area<?> id;
	HashMap<Class<?> , BaseEntity<?,?>> instanceMap;

	
	public ProxyAreaBean() {
		postConstruct();
	}
	
	private void postConstruct() {
		id = new Area<>();
		instanceMap = new HashMap<Class<?> , BaseEntity<?,?>>();
	}

	@Override
	public HashMap<Class<?>, BaseEntity<?, ?>> getInstanceMap() {
		return null;
	}
	
	@Override
	public Class<? extends Area<?>> getBaseClass() {
		return null;
	}
	
	@Override
	public Area<?> getId() {
		return null;
	}
	
	@Override
	public void mountInstanceMap() {
	}
	
	
	@Override
	public boolean registryMethod(Class<?> classId, Class<?> i, String name, Method writeMethod, METHOD_TYPE write) {
		return false;
	}


	@Override
	public ProxyAreaBean<?> getReference() {
		return this;
	}

	@Override
	public <V> Optional<V> writeA(Class<?> clazz, V v) {
		return null;
	}

	@Override
	public <V> Optional<V> writeA(Class<?> ownerClass, Class<?> clazz, V v) {
		return null;
	}

	@Override
	public <V> Optional<?> readB(Class<V> clazz) {
		return null;
	}

	@Override
	public <V> Optional<?> readB(Class<?> ownerClass, Class<V> clazz) {
		return null;
	}
	
	

}
