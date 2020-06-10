package org.nanotek.beans.solr;

import org.nanotek.beans.SolrDocumentBase;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public class Artist extends SolrDocumentBase<Artist> {

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
