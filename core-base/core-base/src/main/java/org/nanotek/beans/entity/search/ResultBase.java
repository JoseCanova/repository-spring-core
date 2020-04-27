package org.nanotek.beans.entity.search;

import javax.persistence.MappedSuperclass;

import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.ResultBaseEntity;

@MappedSuperclass
public class ResultBase
<K extends ResultBase<K>> 
extends SequenceLongBase<K, Long>
implements ResultBaseEntity<K>{

	private static final long serialVersionUID = 8378900415235703087L;

	public ResultBase() {
	}

}
