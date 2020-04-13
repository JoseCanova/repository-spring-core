package org.nanotek.entities;

import java.io.Serializable;

public interface MutableJoinPhraseEntity<K> extends JoinPhraseEntity<K>{

	void setJoinPhrase(K k);
	
}
