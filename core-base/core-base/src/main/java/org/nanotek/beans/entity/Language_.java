package org.nanotek.beans.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-11-07T20:09:11.064-0200")
@StaticMetamodel(Language.class)
public class Language_ extends BrainzBaseEntity_ {
	public static volatile SingularAttribute<Language, Long> languageId;
	public static volatile SingularAttribute<Language, String> isoCode2T;
	public static volatile SingularAttribute<Language, String> isoCode2B;
	public static volatile SingularAttribute<Language, String> isoCode1;
	public static volatile SingularAttribute<Language, Long> frequency;
	public static volatile SingularAttribute<Language, String> isoCode3;
	public static volatile SingularAttribute<Language, String> languageName;
}
