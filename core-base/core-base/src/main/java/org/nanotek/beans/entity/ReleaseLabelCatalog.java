package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseLabelCatalogEntity;
import org.nanotek.entities.MutableReleaseLabelEntity;
import org.nanotek.entities.MutableReleaseLabelNumberEntity;

@Entity
@Table(name="release_label_catalog",
indexes= {
		@Index(unique = false , name = "release_l_catalog_number_idx" , columnList ="catalog_number")
	})
public class ReleaseLabelCatalog<K extends ReleaseLabelCatalog<K>> 
extends BrainzBaseEntity<K>
implements BaseReleaseLabelCatalogEntity<K>,
MutableReleaseLabelNumberEntity<String>,
MutableReleaseLabelEntity<ReleaseLabel<?>>{

	private static final long serialVersionUID = 3417803135828126150L;
	
	@NotBlank
	@Column(name="catalog_number" , nullable = false , columnDefinition = "VARCHAR NOT NULL")
	public String releaseLabelNumber;
	
	@NotNull
	@OneToOne(optional = false , mappedBy = "releaseLabelCatalog")
	public ReleaseLabel<?> releaseLabel;
	
	public ReleaseLabelCatalog() {
		super();
	}

	public ReleaseLabelCatalog(@NotBlank String releaseLabelNumber) {
		super();
		this.releaseLabelNumber = releaseLabelNumber;
	}
	
	public ReleaseLabelCatalog(@NotBlank String releaseLabelNumber, @NotNull ReleaseLabel<?> releaseLabel) {
		super();
		this.releaseLabelNumber = releaseLabelNumber;
		this.releaseLabel = releaseLabel;
	}

	public String getReleaseLabelNumber() {
		return releaseLabelNumber;
	}

	public void setReleaseLabelNumber(String releaseLabelNumber) {
		this.releaseLabelNumber = releaseLabelNumber;
	}

	public ReleaseLabel<?> getReleaseLabel() {
		return releaseLabel;
	}

	public void setReleaseLabel(ReleaseLabel<?> releaseLabel) {
		this.releaseLabel = releaseLabel;
	}
	
}
