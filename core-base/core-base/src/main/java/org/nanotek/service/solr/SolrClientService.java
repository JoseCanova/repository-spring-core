package org.nanotek.service.solr;

import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SolrClientService<K extends BrainzBaseEntity<?>> {
	
	private String collection;
	
	@Autowired
	private SolrOperations solrOperations;

	public SolrClientService() {
	}

	public SolrClientService(String collection) {
		this.collection = collection;
	}
	
	public void saveEntity (K entity) {
		solrOperations.saveBean("", entity);
	}
	
	public Optional<?> findEntity(K entity) { 
		return solrOperations.getById("", entity.getId(), entity.getClass());
	}
	
	public void findBy(Query query , Class<K> clazz) {
		solrOperations.query("", query, clazz);
	}
}
