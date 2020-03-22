package org.nanotek.entities;

import java.util.stream.Stream;

import org.nanotek.beans.entity.Language;
import org.nanotek.stream.KongStream;

public interface MutableBaseLanguageEntity <K extends Language<?>> {

	default Stream<Language> setLanguage(Language k) {
			Class<? extends Language> cl = Language.class.asSubclass(Language.class);
			return KongStream.of(Language.class).add(k).build();
	}
	
}
