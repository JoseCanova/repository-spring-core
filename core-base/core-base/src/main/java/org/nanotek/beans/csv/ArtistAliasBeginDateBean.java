package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.nanotek.entities.BaseArtistAliasBeginDateBean;
import org.nanotek.opencsv.CsvBaseBean;

public class ArtistAliasBeginDateBean
<K extends BaseBean<K,ArtistAliasBeginDate<?>>> 
extends CsvBaseBean<K,ArtistAliasBeginDate<?>>
implements BaseArtistAliasBeginDateBean<K>{

	private static final long serialVersionUID = 1825065026844937439L;

	public ArtistAliasBeginDateBean() {
		super((Class<? extends ArtistAliasBeginDate<?>>) ArtistAliasBeginDate.class);
	}

	public ArtistAliasBeginDateBean(Class<? extends ArtistAliasBeginDate<?>> class1) {
		super(class1);
	}
	
}
