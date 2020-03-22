package org.nanotek.entities;

import org.nanotek.beans.entity.Release;
import org.nanotek.beans.entity.ReleaseAliasBeginDate;
import org.nanotek.beans.entity.ReleaseAliasEndDate;
import org.nanotek.beans.entity.ReleaseAliasLocale;
import org.nanotek.beans.entity.ReleaseAliasSortName;
import org.nanotek.beans.entity.ReleaseAliasType;

public interface MutableReleaseAliasEntity extends 
MutableReleaseAliasIdEntity<Long>,
MutableReleaseAliasLocaleEntity<ReleaseAliasLocale>,
MutableReleaseAliasSortNameEntity<ReleaseAliasSortName>,
MutableReleaseEntity<Release>,
MutableReleaseAliasTypeEntity<ReleaseAliasType>,
MutableReleaseAliasBeginDateEntity<ReleaseAliasBeginDate>,
MutableReleaseAliasEndDateEntity<ReleaseAliasEndDate>{

}
