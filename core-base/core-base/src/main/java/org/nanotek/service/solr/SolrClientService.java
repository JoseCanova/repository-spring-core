package org.nanotek.service.solr;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.stereotype.Service;

@Service
public class SolrClientService<K extends BrainzBaseEntity<?>> {
	
	
	@Autowired
	private SolrOperations solrOperations;

	public SolrClientService() {
	}

	public void saveEntity (K entity) {
		solrOperations.saveBean("", entity);
	}
	
}
