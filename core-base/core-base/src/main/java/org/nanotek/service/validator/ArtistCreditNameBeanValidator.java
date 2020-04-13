package org.nanotek.service.validator;

import java.util.Optional;
import java.util.function.Predicate;

import org.nanotek.beans.csv.ArtistCreditedNameBean;
import org.springframework.stereotype.Component;

@Component
public class ArtistCreditNameBeanValidator implements Predicate<ArtistCreditedNameBean> {

	@Override
	public boolean test(ArtistCreditedNameBean artist) {
		return Optional.ofNullable(artist.getArtistCreditNameId()).orElse(0L) != 0 
				&&  notEmpty(artist.getArtistCreditedName()) 
				&& Optional.ofNullable(artist.getArtistCreditNameId()).orElse(0L) !=0;
	}

	private static boolean notEmpty(String value) {
		return value !=null && !"".equals(value.trim());
	}
}
