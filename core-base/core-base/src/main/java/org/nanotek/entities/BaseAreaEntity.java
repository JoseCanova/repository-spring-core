package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.IdBase;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;

public interface BaseAreaEntity<K extends IdBase<K,Long>> extends  
MutableAreaIdEntity<Long>,		
MutableTypeEntity<AreaType<?>>,
MutableAreaCommentEntity<AreaComment<?>>,
MutableAreaBeginDateEntity<AreaBeginDate<?>>,
MutableAreaEndDateEntity<AreaEndDate<?>>,
MutableGidEntity<UUID>,MutableAreaNameEntity<String>{
}
