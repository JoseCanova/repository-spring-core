package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface LanguageEntity<T extends Serializable> {
T getLanguage();
}
