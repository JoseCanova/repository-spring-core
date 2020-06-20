package org.nanotek.repository.solr;

import java.util.List;

import org.nanotek.beans.solr.Artist;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolrBaseRepository<K extends Artist> extends SolrCrudRepository<K, Long>{	
	
	List<K> findByName(String name);
	
}
