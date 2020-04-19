package au.com.bytecode.opencsv.bean;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nanotek.BaseException;
import org.nanotek.ValueBase;
import org.nanotek.beans.PropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvToBean<T> {

	Logger log = LoggerFactory.getLogger(CsvToBean.class);

	private Map<Class<?>, PropertyEditor> editorMap = null;

	public CsvToBean() {
	}


	public T processLine(MappingStrategy<T> mapper, final List<ValueBase<?>> result) {
		final T bean = mapper.createBean();
		log.debug("List to Process " + result.toString());
		result.stream().forEach(sb -> {
			try { 
				final PropertyDescriptor prop = mapper.findDescriptor(sb.getId());
				if (null != prop) {
//					synchronized (prop) {
						final String value = checkForTrim(sb.getValue(), prop);
						final Object obj = convertValue(value, prop);
						prop.getWriteMethod().invoke(bean, obj);
//					}
				}
			}catch (Exception ex) {
				throw new BaseException(ex);
			}
		});
		return bean;
	}

	private String checkForTrim(final String s, PropertyDescriptor prop) {
		return trimmableProperty(prop) ? s.trim() : s;
	}

	private boolean trimmableProperty(PropertyDescriptor prop) {
		return !prop.getPropertyType().getName().contains("String");
	}

	public Object convertValue(final String value, final PropertyDescriptor prop) throws InstantiationException, IllegalAccessException {
		final PropertyEditor editor = getPropertyEditor(prop);
		if (null != editor) {
			if ("\\N".equals(value) || "N".equals(value)) { 
				editor.setValue(null);
			}else { 
				editor.setAsText(value);
			}
		}
		return editor.getValue();
	}

	public PropertyEditor getPropertyEditorValue(Class<?> cls) {
			return PropertyEditorManager.findEditor(cls);
	}

	protected PropertyEditor getPropertyEditor(PropertyDescriptor desc) throws InstantiationException, IllegalAccessException {
//		Class<?> cls = desc.getPropertyEditorClass();
//		if (null != cls) return (PropertyEditor) cls.newInstance();
		return getPropertyEditorValue(desc.getPropertyType());
	}

}
