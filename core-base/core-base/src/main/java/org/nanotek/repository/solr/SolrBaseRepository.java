package org.nanotek.repository.solr;

import java.util.List;

import org.nanotek.beans.solr.SolrDocumentBase;
import org.springframework.data.solr.repository.SolrRepository;


public interface SolrBaseRepository<K extends SolrDocumentBase<K>> extends SolrRepository<K, Long>{	
	
	List<K> findByName(String name);
	
}
