package org.nanotek.opencsv;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Optional;

import com.sun.beans.*;
import com.sun.beans.finder.*;

import org.assertj.core.util.Arrays;
import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.beans.EntityBeanInfo;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.collections.BaseMap;
import org.springframework.beans.factory.InitializingBean;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

/**
 * Class that works as a bridge between opecsv and the basebeans. 
 * 
 * @author jose
 *
 * @param <T> Base Map defines the configuration of properties and posision on the csv file
 * @param <S> AnyBase for the Map Key 
 * @param <V> AnyBase for the Value Key
 */
@SuppressWarnings("unchecked")
public class MapColumnStrategy
<D extends BaseBean<?,?>> 
extends  ColumnPositionMappingStrategy<D> {

	HashMap<Class<?>, PropertyEditor> editorMap = new HashMap<Class<?>, PropertyEditor>();
	
	Class<D> baseBeanClass;
	
	Class<?> baseEntityClass;
	
	EntityBeanInfo<?> baseBeanClassInfo;
	
	public MapColumnStrategy() {
		super();
	}

	@SuppressWarnings("restriction")
	public <B extends BaseBean<?,?>> 
	MapColumnStrategy(Class<D> type) {
		setType(type);
	}

	//TODO: check a result strategy..
	public <T extends BaseMap<S,V,?> , S extends  AnyBase<S,String>, V extends AnyBase<V,Integer>> 
	void configureMapStrategy(T baseMap) {
		baseMap.afterPropertiesSet();
		assert (baseMap !=null && baseMap.size() >=1);
		String [] csvColumns = new String[baseMap.size()];
						baseMap.keySet().stream().forEach(k -> {
									Optional<Integer> value = (Optional<Integer>) baseMap.get(k).getValue();
									csvColumns[value.get()] = k.getValue().get().toString();
									});
		this.setColumnMapping(csvColumns);
		verifyTypeDeclaration();
		verifyColumnEditorMap();
	}
	
	 private void verifyColumnEditorMap() {
		 Arrays.asList(this.getColumnMapping()).stream().forEach(c -> {
			 if (baseBeanClassInfo.getPropertyDescriptorInfo().get(c) !=null)
				 System.out.println(c);
			 else 
				 System.out.println("NOT FOUND " + c);
		 });
	}

	private void verifyTypeDeclaration() {
		 Class<?> typeClass = getType();
		 TypeVariable<?>[] types = typeClass.getTypeParameters();
		 checkBaseBeanDeclaredEntity(types);
	}

	private void checkBaseBeanDeclaredEntity( TypeVariable<?>[] types) {
		if(types!=null  && types.length==1) {
			Type[] typeBounds = types[0].getBounds();
			Type baseBeanType = typeBounds[0];
			ParameterizedType ptype = ParameterizedType.class.cast(baseBeanType);
			Type[] baseBeanTypeArguments = ptype.getActualTypeArguments();
			ParameterizedType actualBaseBeanType = ParameterizedType.class.cast(baseBeanTypeArguments[0]);
			ParameterizedType actualEntityType = ParameterizedType.class.cast(baseBeanTypeArguments[1]);
			baseBeanClass = Class.class.cast(actualBaseBeanType.getRawType());
			baseEntityClass = Class.class.cast(actualEntityType.getRawType());
			baseBeanClassInfo = new EntityBeanInfo<>(baseBeanClass);
//			Type entityType = typeBounds[1];
		}
	}

	public String getColumnName(int col) {
	        return (null != columnMapping && col < columnMapping.length) ? columnMapping[col] : null ;
	 }
	
	 public PropertyEditor getPropertyEditorValue(Class<?> cls) {
	        if (editorMap == null) {
	            editorMap = new HashMap<Class<?>, PropertyEditor>();
	        }

	        PropertyEditor editor = editorMap.get(cls);

	        if (editor == null) {
	            editor = PropertyEditorManager.findEditor(cls);
	            addEditorToMap(cls, editor);
	        }
    
	        return editor;
	    }

	    private void addEditorToMap(Class<?> cls, PropertyEditor editor) {
	        if (editor != null) {
	            editorMap.put(cls, editor);
	        }
	    }
	
	@Override
	public PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
		return baseBeanClassInfo.getPropertyDescriptorInfo().get(name);
	}   

}
