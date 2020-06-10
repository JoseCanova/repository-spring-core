package org.nanotek.service.http;

import java.util.Optional;

import org.nanotek.beans.SolrDocumentBase;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.solr.SolrBaseRepository;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.nanotek.service.solr.SolrClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path={"/solr"},produces = MediaType.APPLICATION_JSON_VALUE)
public class SolrBrainzController 
<K extends BrainzBaseEntity<K>,B extends SolrDocumentBase<B>>{

	@Autowired 
	SolrBaseRepository<org.nanotek.beans.solr.Artist> solrBaseRepository;
	
	@Autowired
	SolrClientService<B> solrClientService;
	
	@Autowired
	BrainzPersistenceService<K> brainzPersistenceService;
	
	@GetMapping(path = "/artist/id/{id}")
	@Transactional
	public <S extends B> ResponseEntity<?> findArtist(@PathVariable(value="id") Long  id) {
		return indexAndMountResponse(id);
	}

	@Transactional
	private ResponseEntity<?> indexAndMountResponse(Long id) {
		ForwardMapBean<K> fm = new ForwardMapBean<>(Artist.class);
		fm.write("artistId", id);
		Artist<?> entity = (Artist<?>) brainzPersistenceService.findByExample(Example.of(fm.to()));
		org.nanotek.beans.solr.Artist solrBean = new org.nanotek.beans.solr.Artist();
		solrBean.setArtistId(entity.getArtistId());
		solrBean.setName(entity.getArtistName());
		solrClientService.saveEntity((B) solrBean);
//		solrBaseRepository.save(solrBean);
		return returnResponse(Optional.of(solrBean));
	}
	
	private  <S extends org.nanotek.beans.solr.Artist> ResponseEntity<?> returnResponse(Optional<S> opt) {
		HttpStatus status =  opt.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(opt,status);
	}
	
}
