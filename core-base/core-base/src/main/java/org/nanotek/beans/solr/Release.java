package org.nanotek.beans.solr;

import org.nanotek.beans.SolrDocumentBase;
import org.nanotek.entities.MutableNameEntity;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection="brainz_core")
public class Release 
extends SolrDocumentBase<Release>
implements 
MutableNameEntity<String>{

	private static final long serialVersionUID = -6672601341315269078L;
	
	@Indexed(name = "name" , type = "string")
	public String name;

	public Release() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
