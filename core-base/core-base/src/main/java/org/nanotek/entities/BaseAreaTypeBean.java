package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.AreaTypeBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.immutables.ChildOrderEntity;


/**
 * 	public Long typeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
 * @author jose
 *
 * @param <K>
 */
public interface BaseAreaTypeBean
<K extends BaseBean<K,AreaType<?>>> 
extends Base<K>,
BaseBean<K,AreaType<?>>,
MutableGidEntity<UUID>,
MutableNameEntity<String>,
MutableTypeIdEntity<Long>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableBaseTypeDescriptionEntity<BaseBaseTypeDescriptionBean<?>>,
MutableDescriptionBaseEntity<String>{

	@Override
	default void setTypeId(Long k) {
		write(MutableTypeIdEntity.class,k);
	}

	@Override
	default Long getTypeId() {
		return read(TypeIdEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}

	@Override
	default void setParent(Long k) {
		write(MutableParentEntity.class , k);
	}

	@Override
	default Long getParent() {
		return read(ParentEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}

	@Override
	default void setChildOrder(Long k) {
		write(MutableChildOrderEntity.class,k);
	}

	@Override
	default Long getChildOrder() {
		return read(ChildOrderEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}

	@Override
	default String getName() {
		return read(NameEntity.class).map(s->String.class.cast(s)).orElse("");
	}

	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}

	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g ->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}

	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}

	@Override
	default String getDescription() {
		return getBaseTypeDescription().getDescription();
	}

	@Override
	default void setDescription(String k) {
		getBaseTypeDescription().setDescription(k);
	}


	public static  void main(String[] args) {
		AreaTypeBean bean = new AreaTypeBean(AreaType.class);
		bean.setName("name");
		System.out.println(bean.getName());
		bean.setParent(1000L);
		System.out.println(bean.getParent());
		bean.setDescription("this is  a rescription");
		System.out.println(bean.getDescription());
		bean.setChildOrder(100L);
		System.out.println(bean.getChildOrder());
		bean.setTypeId(1000l);
		System.out.println(bean.getTypeId());
		bean.setGid(UUID.randomUUID());
		System.out.println(bean.getGid().toString());
		
	}

}
