package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;

public interface BaseAreaEntity<T> extends  
MutableAreaIdEntity<Long>,		
MutableTypeEntity<AreaType<?>>,
MutableAreaCommentEntity<AreaComment<?>>,
MutableAreaBeginDateEntity<AreaBeginDate<?>>,
MutableAreaEndDateEntity<AreaEndDate<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>{
}
