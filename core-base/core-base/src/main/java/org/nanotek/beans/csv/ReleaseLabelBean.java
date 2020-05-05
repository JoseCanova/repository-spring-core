package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseLabel;
import org.nanotek.entities.BaseLabelBean;
import org.nanotek.entities.BaseReleaseBean;
import org.nanotek.entities.BaseReleaseLabelBean;
import org.nanotek.entities.BaseReleaseLabelCatalogBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseLabelBean 
<K extends BaseBean<ReleaseLabelBean<K>,ReleaseLabel<?>>> 
extends ProxyBase<ReleaseLabelBean<K>,ReleaseLabel<?>>
implements BaseReleaseLabelBean<ReleaseLabelBean<K>>{

	private static final long serialVersionUID = -3156858165474932694L;

	private BaseReleaseLabelCatalogBean<?> releaseLabelCatalog;
	
	private BaseLabelBean<?> labelRelease;
	
	private BaseReleaseBean<?> release;
	
	public ReleaseLabelBean() {
		super(castClass());
		postConstructReleaseLabel();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseLabel<?>> castClass() {
		return (Class<? extends ReleaseLabel<?>>) 
				ReleaseLabel.class.asSubclass(ReleaseLabel.class);
	}
	
	public ReleaseLabelBean(Class<ReleaseLabel<?>>id) {
		super(id);
		postConstructReleaseLabel();
	}
	
	private void postConstructReleaseLabel() {
		releaseLabelCatalog = new ReleaseLabelCatalogBean<>();
		labelRelease = new LabelBean<>();
		release = new ReleaseBean<>();
	}

	public BaseReleaseLabelCatalogBean<?> getReleaseLabelCatalog() {
		return releaseLabelCatalog;
	}

	public void setReleaseLabelCatalog(BaseReleaseLabelCatalogBean<?> releaseLabelCatalog) {
		this.releaseLabelCatalog = releaseLabelCatalog;
	}

	public BaseLabelBean<?> getLabelRelease() {
		return labelRelease;
	}

	public void setLabelRelease(BaseLabelBean<?> labelRelease) {
		this.labelRelease = labelRelease;
	}

	public BaseReleaseBean<?> getRelease() {
		return release;
	}

	public void setRelease(BaseReleaseBean<?> release) {
		this.release = release;
	}

	
	
}
