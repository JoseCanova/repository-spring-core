package org.nanotek.beans.solr;

import org.nanotek.SolrBase;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public class SolrDocumentBase<K extends SolrBase<K>> implements SolrBase<K> {

	private static final long serialVersionUID = 1798347613053387449L;

	@Id
	@Indexed(name = "artistId")
	public Long artistId;

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}
	
}
