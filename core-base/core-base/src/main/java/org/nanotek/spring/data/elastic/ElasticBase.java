package org.nanotek.spring.data.elastic;

import java.io.Serializable;

import org.nanotek.IdBase;

public interface ElasticBase
<K extends ElasticBase<K,ID>, ID extends Serializable> 
extends IdBase<K , ID> {
}
