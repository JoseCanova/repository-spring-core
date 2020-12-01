package org.nanotek.spring.data.elastic.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ElasticBaseService<B extends BrainzBaseEntity<B>> {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	static Map<Class<?> ,String> coordinatesCache = new HashMap<Class<?> , String>();
	
	public B save(B b) {
		String coordinates = Optional.ofNullable(coordinatesCache.get(b.getClass())).orElse(withCoordinates(b.getClass()));
		if(!coordinates.isEmpty()) {
			IndexCoordinates ic = IndexCoordinates.of(new String[]{coordinates});
			IndexQuery indexQuery = new IndexQueryBuilder()
					.withId(b.getId().toString())
					.withObject(b)
					.build();
			elasticsearchRestTemplate.index(indexQuery,ic);
		}
		return b;
	}

	private String withCoordinates(Class<?> class1) {
		Document document = class1.getAnnotation(Document.class);
		return Optional.ofNullable(document).map(v -> {coordinatesCache.put(class1, v.indexName())  ; return v.indexName();}).orElse("");
	}
}
