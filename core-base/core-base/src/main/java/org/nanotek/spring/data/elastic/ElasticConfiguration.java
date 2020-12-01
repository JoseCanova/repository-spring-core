package org.nanotek.spring.data.elastic;

import java.net.UnknownHostException;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"org.nanotek.spring.data.elastic.repository"})
public class ElasticConfiguration extends AbstractElasticsearchConfiguration {

  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {
    return RestClients
    			.create(ClientConfiguration.create("localhost:9200"))
    			.rest();
  }
  
  @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate" , "elasticsearchRestTemplate"})
  public ElasticsearchRestTemplate elasticsearchTemplate() throws UnknownHostException { 
  	return new ElasticsearchRestTemplate(elasticsearchClient());
  }
}