package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.JoinPhraseBase;
import org.nanotek.entities.BaseJoinPhraseBean;
import org.nanotek.proxy.ProxyBase;

//TODO:use just for test.
public class JoinPhraseBean 
<K extends BaseBean<JoinPhraseBean<K>,JoinPhraseBase<String,?>>> 
extends ProxyBase<JoinPhraseBean<K>,JoinPhraseBase<String,?>>
implements BaseJoinPhraseBean<JoinPhraseBean<K>>{

	public JoinPhraseBean(Class<? extends JoinPhraseBase<String, ?>> class1) {
		super(class1);
	}

	@Override
	public void setJoinPhrase(String k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getJoinPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

}
