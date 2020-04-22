package org.nanotek.entities;

import org.nanotek.entities.immutables.LanguageNameEntity;

public interface MutableLanguageNameEntity<T> extends  LanguageNameEntity<T>{
 void setLanguageName(T t);
}
