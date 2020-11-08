package org.nanotek.spring.data.elastic.model;

import org.nanotek.spring.data.elastic.ElasticId;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Document(indexName = "artist_credit_index" , createIndex=true)
@Setting(settingPath = "/elastic_artist_credit.json")
public class ElasticArtistCredit<K extends ElasticArtistCredit<K>> extends ElasticId<K>{

	private static final long serialVersionUID = 8377408621868403972L;

	@Field(name="name" , analyzer = "simple_analyzer")
	public String artistCreditName;
	
	public String getArtistCreditName() {
		return artistCreditName;
	}

	public void setArtistCreditName(String artistCreditName) {
		this.artistCreditName = artistCreditName;
	}

}
