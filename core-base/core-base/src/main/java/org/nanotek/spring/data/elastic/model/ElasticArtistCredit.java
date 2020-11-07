package org.nanotek.spring.data.elastic.model;

import org.nanotek.spring.data.elastic.ElasticId;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "brainz_artist_credit_index")
public class ElasticArtistCredit<K extends ElasticArtistCredit<K>> extends ElasticId<K>{

	private static final long serialVersionUID = 8377408621868403972L;

	@Field
	public String artistCreditName;
	
	public String getArtistCreditName() {
		return artistCreditName;
	}

	public void setArtistCreditName(String artistCreditName) {
		this.artistCreditName = artistCreditName;
	}

}
