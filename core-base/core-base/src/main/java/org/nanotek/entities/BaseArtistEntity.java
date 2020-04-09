package org.nanotek.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.nanotek.MutatorSupport;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.beans.entity.ArtistComment;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.ArtistEndDate;
import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.beans.entity.ArtistType;
import org.nanotek.beans.entity.Gender;

public interface BaseArtistEntity<K> extends MutatorSupport<K>,
MutableArtistIdEntity<Long>,
MutableArtistSortNameEntity<ArtistSortName<?>>,
MutableArtistCommentEntity<ArtistComment<?>>,
MutableArtistBeginDateEntity<ArtistBeginDate<?>>,
MutableArtistEndDateEntity<ArtistEndDate<?>>,
MutableArtistTypeEntity<ArtistType<?>>,
MutableGenderEntity<Gender<?>>,
MutableAreaEntity<Area<?>>,
MutableArtistBeginAreaEntity<Area<?>>,
MutableArtistEndAreaEntity<Area<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>,
MutableArtistCreditCollection<List<ArtistCredit<?>>>{
	
	@Override
	Area<?> getArea();
	
	@Override
	void setName(String k);

}
