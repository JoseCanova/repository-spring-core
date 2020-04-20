package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Instrument;
import org.nanotek.entities.BaseInstrumentBean;
import org.nanotek.entities.BaseInstrumentCommentBean;
import org.nanotek.entities.BaseInstrumentDescriptionBean;
import org.nanotek.entities.BaseInstrumentTypeBean;
import org.nanotek.proxy.ProxyBase;

public class InstrumentBean
<K extends BaseBean<InstrumentBean<K>,Instrument<?>>> 
extends ProxyBase<InstrumentBean<K>,Instrument<?>>
implements BaseInstrumentBean<InstrumentBean<K>>{


	private static final long serialVersionUID = -6916258778573566572L;
	
	private BaseInstrumentCommentBean<?> instrumentComment;
	
	private BaseInstrumentDescriptionBean<?> instrumentDescription;
	
	private BaseInstrumentTypeBean<?> instrumentType;
	
	
	
//	public Long instrumentId; 
//	public String gid; 
//	public String name; 
//	public Long type; 
//	public Integer editsPending; 
//	public String lastUpdatead; 
//	public String comment; 
//	public String description;
	
	public InstrumentBean() {
		super(castClass());
		postConstruct() ;
	}

	private void postConstruct() { 
		instrumentComment = new InstrumentCommentBean<> ();
		
		instrumentDescription = new InstrumentDescriptionBean<> ();
		
		instrumentType = new InstrumentTypeBean<>() ;
		
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends Instrument<?>> castClass() {
		return (Class<? extends Instrument<?>>) Instrument.class.asSubclass(Instrument.class);
	}

	public InstrumentBean(Class<? extends Instrument<?>> class1) {
		super(class1);
		postConstruct() ;
	}

	public BaseInstrumentCommentBean<?> getInstrumentComment() {
		return instrumentComment;
	}

	public void setInstrumentComment(BaseInstrumentCommentBean<?> instrumentComment) {
		this.instrumentComment = instrumentComment;
	}

	public BaseInstrumentDescriptionBean<?> getInstrumentDescription() {
		return instrumentDescription;
	}

	public void setInstrumentDescription(BaseInstrumentDescriptionBean<?> instrumentDescription) {
		this.instrumentDescription = instrumentDescription;
	}

	public BaseInstrumentTypeBean<?> getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(BaseInstrumentTypeBean<?> instrumentType) {
		this.instrumentType = instrumentType;
	}

}
