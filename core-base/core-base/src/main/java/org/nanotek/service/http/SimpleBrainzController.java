package org.nanotek.service.http;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.nanotek.MapBase;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.proxy.map.bean.ForwardMapBean;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.nanotek.service.search.BaseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple_brainz")
public class SimpleBrainzController
<B extends BrainzBaseEntity<B>,K extends MapBase<K>> 
extends BrainzPersistenceService<B>
{
	
	public SimpleBrainzController(@Autowired
			BrainzBaseEntityRepository<B> repository) {
		super(repository);
	}

	@Autowired
	@Qualifier("BaseSearchService")
	public  BaseSearchService<B> baseSearchService;
	
	@GetMapping(value="/{type}")
	public ResponseEntity<?> getAll(@PathVariable(value = "type") String brainzRepresentationType){
		return prepareAllResponse(SimpleTypeResolver.resolve(brainzRepresentationType));
	}
	
	
	private  <S extends B>  ResponseEntity<?> prepareAllResponse(SimpleTypeResolver classEnum ) {
		return returnAllResponse(classEnum);
	}
	
	@Transactional
	private  <S extends B> ResponseEntity<?> returnAllResponse(SimpleTypeResolver classEnum) {
		ForwardMapBean<S> fm = Optional.ofNullable(classEnum).map(c -> new ForwardMapBean<S>(c.getBrainzClazz())).orElse(null);
		List<S> result = Optional.ofNullable(fm).map(f -> findAll(Example.of(fm.to()))).orElse(null);
		HttpStatus status =  result !=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(result,status);
	}
	
	enum SimpleTypeResolver {
		
		AREA_TYPE("area_type" , AreaType.class);
		
		String type; 
		
		Class<?> brainzClazz;
		
		private <B extends BrainzBaseEntity<?>> SimpleTypeResolver(String type , Class<B> brainzClass) {
			this.brainzClazz = brainzClass;
			this.type=type;
		}

		public String getType() {
			return type;
		}

		public <S extends B , B extends BrainzBaseEntity<B>> Class<B> getBrainzClazz() {
			return (Class<B>) brainzClazz;
		}
		
		public static SimpleTypeResolver resolve(String type) {
			   switch(type) {
			   case "area_type": 
				   return AREA_TYPE;
				 default:
					 return null;
			   }
		}
		
	}
}
