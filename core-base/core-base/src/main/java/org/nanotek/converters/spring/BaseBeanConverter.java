package org.nanotek.converters.spring;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class BaseBeanConverter<K extends BaseBean<?,?>> 
implements Converter<String, BaseBean<?,?>>{

	public BaseBeanConverter() {
	}

	@Override
	public BaseBean<?, ?> convert(String source) {
		Optional.ofNullable(source).orElseThrow(BaseException::new);
		try {
				final Class<K> sClass = (Class<K>) Class.forName(source);
				return Base.newInstance(sClass).get();				
		}catch(Exception ex) { 
			throw new BaseException(ex);
		}
	}

}
