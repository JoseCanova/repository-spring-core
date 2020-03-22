package org.nanotek.entities.immutables;

import java.util.stream.Stream;

import org.nanotek.beans.entity.Language;
import org.nanotek.entities.MutableBaseLanguageEntity;

public interface BaseLanguageEntity<K extends Language<?>> extends  MutableBaseLanguageEntity<K>{
	@SuppressWarnings("rawtypes")
	Stream<Language> getLanguage();

}
