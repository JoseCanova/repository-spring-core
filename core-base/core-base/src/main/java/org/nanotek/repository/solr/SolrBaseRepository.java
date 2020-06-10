package org.nanotek.repository.solr;

import java.util.List;

import org.nanotek.beans.solr.Artist;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface SolrBaseRepository<K extends Artist> extends SolrCrudRepository<K, Long>{	
	
	List<K> findByName(String name);
	
}
