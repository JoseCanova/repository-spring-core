package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.spring.data.elastic.repository.ElasticArtistCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationStartTest {

	
	@Autowired 
	ApplicationContext context;
	
	@Autowired
	ElasticArtistCreditRepository elasticArtistCreditRepository;
	
	@Autowired
	ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	@Test
    @Order(1)    
	void testSave() {
		IndexCoordinates ic = IndexCoordinates.of(new String[]{hasCoordinates(ArtistCredit.class)});
		ArtistCredit<?> artistCredit = new ArtistCredit<>();
		artistCredit.setId(1L);
		artistCredit.setArtistCreditId(1L);
		artistCredit.setArtistCreditName("Iron Maiden Festival on Credits");
		 IndexQuery indexQuery = new IndexQueryBuilder()
			      .withId(artistCredit.getId().toString())
			      .withObject(artistCredit)
			      .build();
			    String documentId = elasticsearchRestTemplate.index(indexQuery,ic);
			    System.err.println(documentId);
			    ArtistCredit<?> imHere = elasticsearchRestTemplate.get(documentId, ArtistCredit.class , ic);
		assertNotNull(imHere);
	}

	private String hasCoordinates(Class<?> class1) {
		Document document = class1.getAnnotation(Document.class);
		return Optional.ofNullable(document).map(v -> v.indexName()).orElseThrow(RuntimeException::new);
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Test
    @Order(2)    
	void testFind() {
		ArtistCredit<?> artistCredit = new ArtistCredit<>();
		artistCredit.setId(1L);
		artistCredit.setArtistCreditId(1L);
		artistCredit.setArtistCreditName("Iron Maiden Festival on Credits");
		List<ArtistCredit<?>> list = elasticArtistCreditRepository.findByArtistCreditNameLike("Iron");
		assertTrue(list.size() >0);
	}

	
}
