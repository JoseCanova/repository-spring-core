package org.nanotek.web;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

//	@Autowired ArtistJpaService artistJpaService;
	
	public ControllerConfig() {
	}
	
	/*
	 * @Bean public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
	 * SimpleUrlHandlerMapping simpleUrlHandlerMapping = new
	 * SimpleUrlHandlerMapping();
	 * 
	 * Map<String, Object> urlMap = new HashMap<>();
	 * urlMap.put("/artist_controller/*", baseArtistControllerConfig());
	 * simpleUrlHandlerMapping.setUrlMap(urlMap); return simpleUrlHandlerMapping; }
	 * 
	 * @Bean public EntityResponseController<Artist,Long>
	 * baseArtistControllerConfig(){ return new
	 * EntityResponseController<Artist,Long>(){
	 * 
	 * @Override public BasePersistenceService<Artist, Long> getBaseService() {
	 * return artistJpaService; }
	 * 
	 * }; }
	 */

}
