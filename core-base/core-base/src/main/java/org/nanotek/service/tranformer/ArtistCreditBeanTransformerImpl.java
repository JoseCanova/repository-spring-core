package org.nanotek.service.tranformer;

import org.nanotek.beans.csv.ArtistCreditBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value="ArtistCreditBeanTransformer")
public class ArtistCreditBeanTransformerImpl implements ArtistCreditBeanTransformer {

    @Override
    public ArtistCredit<?> transform(ArtistCreditBean i) {
        if ( i == null ) {
            return null;
        }

        ArtistCredit artistCredit = new ArtistCredit();

        artistCredit.setArtistCreditId(i.getArtistCreditId()  );
        artistCredit.setName( i.getName() );
		/*
		 * if ( i.getArtistCount() != null ) { artistCredit.setArtistCount(
		 * i.getArtistCount().longValue() ); } if ( i.getRefCount() != null ) {
		 * artistCredit.setRefCount( i.getRefCount().longValue() ); }
		 */

        return artistCredit;
    }
}