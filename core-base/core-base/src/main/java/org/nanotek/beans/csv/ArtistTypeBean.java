package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.ArtistType;
import org.nanotek.entities.BaseArtistTypeBean;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistTypeBean
<K extends BaseBean<ArtistTypeBean<K>,ArtistType<?>>> 
extends ProxyBase<ArtistTypeBean<K>,ArtistType<?>>
implements BaseArtistTypeBean<ArtistTypeBean<K>>{

	private static final long serialVersionUID = -6271568961378072618L;


	//	public Long artistTypeId; 
	//	public String name; 
	//	public Long parent; 
	//	public Long childOrder; 
	//	public String description; 
	//	public String gid;
	//	


	BaseBaseTypeDescriptionBean<?>  baseTypeDescription;

	public ArtistTypeBean() {
		super(castClass());
		postConstruction();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistType<?>> castClass() {
		return (Class<? extends ArtistType<?>>) ArtistType.class.asSubclass(ArtistType.class);
	}
	
	private void postConstruction() {
		baseTypeDescription = new BaseTypeDescriptionBean<>();
	}

	public ArtistTypeBean(Class<? extends ArtistType<?>> class1) {
		super(class1);
		postConstruction();
	}

	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}

	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}




}
