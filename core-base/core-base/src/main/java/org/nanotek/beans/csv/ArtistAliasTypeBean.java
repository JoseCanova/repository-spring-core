package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.entities.BaseArtistAliasTypeBean;
import org.nanotek.opencsv.CsvBaseBean;

/*
 * public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>
 * 
 */

public class ArtistAliasTypeBean
<K extends BaseBean<K,ArtistAliasType<?>>> 
extends CsvBaseBean<ArtistAliasType<?>>
implements  BaseArtistAliasTypeBean<K>{

	private static final long serialVersionUID = 901207660901713562L;
	

	public Long artistAliasTypeId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	
}
