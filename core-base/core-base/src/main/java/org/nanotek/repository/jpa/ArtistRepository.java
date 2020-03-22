package org.nanotek.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.nanotek.beans.entity.Artist;
import org.nanotek.repository.jpa.projections.GidBaseProjection;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository<K extends Artist<K>> extends BrainzBaseRepository<K>,
NameBaseProjection<K,String>,
GidBaseProjection<K,String>{
	
	Optional<K> findByArtistId(Long artistId);
	
	@Query("select new org.nanotek.beans.entity.Artist(a.id , a.name , a.gid) "
			+ "from Artist a where UPPER(a.name) like %:name% order by a.name")
	List<K> findByNameEspec(@Param("name")String name);

}
