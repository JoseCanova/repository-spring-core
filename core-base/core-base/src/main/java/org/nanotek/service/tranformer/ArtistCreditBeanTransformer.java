package org.nanotek.service.tranformer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nanotek.EntityBaseTransformer;
import org.nanotek.beans.csv.ArtistCreditBean;
import org.nanotek.beans.entity.ArtistCredit;

@Mapper(componentModel = "spring")
public interface ArtistCreditBeanTransformer extends EntityBaseTransformer<ArtistCreditBean,ArtistCredit>{

	@Mapping(target = "id", source = "id")
	@Override
	ArtistCredit transform(ArtistCreditBean i);
}
