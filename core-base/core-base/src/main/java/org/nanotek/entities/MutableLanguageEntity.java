package org.nanotek.entities;

import java.io.Serializable;

public interface MutableLanguageEntity <K extends Serializable> extends 					MutableLanguageIdEntity<Long>,
																							MutableIsoCode2TEntity<String>,
																							MutableIsoCode2BEntity<String>,
																							MutableFrequencyEntity<Long>,
																							MutableIsoCode3Entity<String> 
{
}
