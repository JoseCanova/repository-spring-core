package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LanguageEntity;

public interface MutableLanguageEntity <K> extends LanguageEntity<K>
{void setLanguage(K k);
}
