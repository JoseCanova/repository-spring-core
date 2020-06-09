package org.nanotek.beans.solr;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public class Artist extends SolrDocumentBase<Artist> {

	private static final long serialVersionUID = -2918232114013519078L;

	@Indexed(name = "name" , type = "string")
	public String artistName;
	
	
	public Artist() {
	}


	public String getArtistName() {
		return artistName;
	}


	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
}
