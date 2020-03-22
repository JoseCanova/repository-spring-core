package org.nanotek.service.tranformer;

import java.util.Optional;

import org.nanotek.Transformer;
import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.service.jpa.ArtistCreditJpaService;
import org.nanotek.service.jpa.ArtistJpaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistCreditNameTransformer<K extends ArtistCredit<K>> implements Transformer<ArtistCreditNameBean,ArtistCreditName> {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());


	@Override
	@Transactional
	public ArtistCreditName transform(ArtistCreditNameBean i) {
		return null;
	}


	private ArtistCreditName populate(ArtistCreditNameBean i, 
			Optional<ArtistCredit> artistCredit,
			Optional<Artist> Artist) {
		return null;
	}

}
