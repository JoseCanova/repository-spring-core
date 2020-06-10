package org.nanotek.service.solr;

import java.util.Optional;

import org.nanotek.beans.SolrDocumentBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SolrClientService<K extends SolrDocumentBase<K>> {
	
	private String collectionName;
	
	@Autowired
	private SolrOperations solrOperations;

	public SolrClientService() {
		this.collectionName  = "brainz_core";
	}

	public SolrClientService(String collection) {
		this.collectionName = collection;
	}
	
	public void saveEntity (K entity) {
		solrOperations.saveBean(collectionName, entity);
		solrOperations.commit(collectionName);
	}
	
	public Optional<?> findEntity(K entity) { 
		return solrOperations.getById(collectionName, entity.getArtistId(), entity.getClass());
	}
	
	public void findBy(Query query , Class<K> clazz) {
		solrOperations.query(collectionName, query, clazz);
	}
}
