package au.com.bytecode.opencsv.bean;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nanotek.BaseException;
import org.nanotek.ValueBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvToBean<T> {
	
	Logger log = LoggerFactory.getLogger(CsvToBean.class);
	
    private Map<Class<?>, PropertyEditor> editorMap = null;

    public CsvToBean() {
    }


    public T processLine(MappingStrategy<T> mapper, List<ValueBase<?>> result) {
        T bean = mapper.createBean();
        result.stream().forEach(sb -> {
        	try { 
        	PropertyDescriptor prop = mapper.findDescriptor(sb.getId());
            String value = null;
            Object obj = null;
	            if (null != prop) {
	            	log.debug(prop.toString());
	            	log.debug(sb.getClass() + "  "  + sb.getId());
	                value = checkForTrim(sb.getValue(), prop);
	                obj = convertValue(value, prop);
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
