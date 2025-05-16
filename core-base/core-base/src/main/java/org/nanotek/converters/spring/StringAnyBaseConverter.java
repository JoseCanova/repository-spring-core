package org.nanotek.converters.spring;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class StringAnyBaseConverter<S extends Base<S>,K extends Comparable<K>,A extends AnyBase<S,K>> 
implements Converter<String, AnyBase<S,K>> {

	@Override
	public AnyBase<S, K> convert(String source) {
		return AnyBase.class.cast(AnyBase.of(source));
	}

}
