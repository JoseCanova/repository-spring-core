package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.entities.BaseArtistAliasTypeBean;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.proxy.ProxyBase;

/*
 * public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>
 * 
 */
public class ArtistAliasTypeBean
<K extends BaseBean<ArtistAliasTypeBean<K>,ArtistAliasType<?>>> 
extends ProxyBase<ArtistAliasTypeBean<K>,ArtistAliasType<?>>
implements  BaseArtistAliasTypeBean<ArtistAliasTypeBean<K>>{

	private static final long serialVersionUID = 901207660901713562L;

	BaseBaseTypeDescriptionBean<?> baseTypeDescription;
	
	public ArtistAliasTypeBean() {
		super(castClass());
		postConstruct();
	}
	
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistAliasType<?>>castClass() {
		return (Class<? extends ArtistAliasType<?>>) 
				ArtistAliasType.class.
				asSubclass(ArtistAliasType.class);
	}
	
	private void postConstruct() {
		baseTypeDescription = new  BaseTypeDescriptionBean<>();
	}
	
	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}


	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}


	public ArtistAliasTypeBean(Class<? extends ArtistAliasType<?>> class1) {
		super(class1);
		postConstruct();
	}
	
	
}
