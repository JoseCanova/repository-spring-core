package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseLocaleBaseEntity;
import org.nanotek.entities.MutableLocaleEntity;

@Entity
@Table(name="locale_base",
					indexes= {
							@Index(unique = false , name = "locale_table_idx" , columnList ="locale")
						})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public class LocaleBase<K extends LocaleBase<K>> 
extends BrainzBaseEntity<K> implements 
																		BaseLocaleBaseEntity<K>,
																		MutableLocaleEntity<String> {

	private static final long serialVersionUID = -6664969453930737424L;

	@NotBlank
	@Column(name="locale", columnDefinition="VARCHAR NOT NULL")
	protected String locale; 
	
	public LocaleBase() {
	}
	
	public LocaleBase(@NotBlank String locale) {
		super();
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
