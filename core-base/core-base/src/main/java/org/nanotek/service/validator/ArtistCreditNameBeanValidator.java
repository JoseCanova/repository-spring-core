package org.nanotek.service.validator;

import java.util.Optional;
import java.util.function.Predicate;

import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.springframework.stereotype.Component;

@Component
public class ArtistCreditNameBeanValidator implements Predicate<ArtistCreditNameBean> {

	@Override
	public boolean test(ArtistCreditNameBean artist) {
		return Optional.ofNullable(artist.getArtistCreditId()).orElse(0L) != 0 
				&&  notEmpty(artist.getName()) 
				&& Optional.ofNullable(artist.getArtistId()).orElse(0L) !=0;
	}

	private static boolean notEmpty(String value) {
		return value !=null && !"".equals(value.trim());
	}
}
