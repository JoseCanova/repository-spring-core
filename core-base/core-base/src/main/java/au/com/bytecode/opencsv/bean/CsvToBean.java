package au.com.bytecode.opencsv.bean;

/**
 Copyright 2007 Kyle Miller.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nanotek.BaseException;
import org.nanotek.StringPositionBase;

public class CsvToBean<T> {
	
    private Map<Class<?>, PropertyEditor> editorMap = null;

    public CsvToBean() {
    }


    public T processLine(MappingStrategy<T> mapper, List<StringPositionBase<?>> result) 
    		throws IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        T bean = mapper.createBean();
        
        result.stream().forEach(sb -> {
        	try { 
        	PropertyDescriptor prop = mapper.findDescriptor(sb.getPosition());
            if (null != prop) {
                String value = checkForTrim(sb.getId(), prop);
                Object obj = convertValue(value, prop);
                prop.getWriteMethod().invoke(bean, obj);
            }
        	}catch (Exception ex) { 
        		throw new BaseException(ex);
        	}
        });
        return bean;
    }

    private String checkForTrim(String s, PropertyDescriptor prop) {
        return trimmableProperty(prop) ? s.trim() : s;
    }

    private boolean trimmableProperty(PropertyDescriptor prop) {
        return !prop.getPropertyType().getName().contains("String");
    }

    public Object convertValue(String value, PropertyDescriptor prop) throws InstantiationException, IllegalAccessException {
        PropertyEditor editor = getPropertyEditor(prop);
        Object obj = value;
        if (null != editor) {
        	if ("\\N".equals(value) || "N".equals(value)) { 
        		editor.setValue(null);
			}else { 
				editor.setAsText(value);
			}
            obj = editor.getValue();
        }
        return obj;
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

    /* TODO: change for other strategy
     * Attempt to find custom property editor on descriptor first, else try the propery editor manager.
     */
    protected PropertyEditor getPropertyEditor(PropertyDescriptor desc) throws InstantiationException, IllegalAccessException {
        Class<?> cls = desc.getPropertyEditorClass();
        if (null != cls) return (PropertyEditor) cls.newInstance();
        return getPropertyEditorValue(desc.getPropertyType());
    }

}
