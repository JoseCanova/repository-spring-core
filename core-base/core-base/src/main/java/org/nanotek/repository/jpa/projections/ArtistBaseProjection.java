package org.nanotek.repository.jpa.projections;

import java.util.Optional;

import org.nanotek.base.bean.projections.ArtistVirtualProjection;
import org.nanotek.beans.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ArtistVirtualProjection.class)
public interface ArtistBaseProjection extends JpaRepository<Artist,Long> {
	Optional<ArtistVirtualProjection> findByArtistId(@Param("artistId")Long artistId);
}
