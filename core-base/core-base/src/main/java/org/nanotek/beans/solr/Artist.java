package org.nanotek.beans.solr;

import org.nanotek.beans.SolrDocumentBase;
import org.nanotek.entities.MutableNameEntity;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection="brainz_core")
public class Artist 
extends SolrDocumentBase<Artist> 
implements 
MutableNameEntity<String>{

	private static final long serialVersionUID = -2918232114013519078L;

	@Indexed(name = "name" , type = "string")
	public String name;
	
	public Artist() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
