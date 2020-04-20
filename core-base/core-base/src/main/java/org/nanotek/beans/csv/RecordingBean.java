package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Recording;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseRecordingBean;
import org.nanotek.entities.BaseRecordingCommentBean;
import org.nanotek.entities.BaseRecordingLengthBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingBean  
<K extends BaseBean<RecordingBean<K>,Recording<?>>> 
extends ProxyBase<RecordingBean<K>,Recording<?>>
implements BaseRecordingBean<RecordingBean<K>>{

	private static final long serialVersionUID = 2926594064752891481L;

	private BaseArtistCreditBean<?> artistCredit;
	private BaseRecordingLengthBean<?> recordingLength;
	private BaseRecordingCommentBean<?> recordingComment;
	
	public RecordingBean(Class<? extends Recording<?>> class1) {
		super(class1);
		postConstruct();
	}

	public  void postConstruct()
	{
		artistCredit = new ArtistCreditBean<>();
		recordingLength = new RecordingLengthBean<>();
		recordingComment = new RecordingCommentBean<>();
	}
	
	public RecordingBean() {
		super(castClass());
		postConstruct();
	}

	private static Class<? extends Recording<?>> castClass() {
		return (Class<? extends Recording<?>>) Recording.class.asSubclass(Recording.class);
	}

	@Override
	public void setArtistCredit(BaseArtistCreditBean<?> k) {
		this.artistCredit = k;		
	}

	@Override
	public BaseArtistCreditBean<?> getArtistCredit() {
		return artistCredit;
	}

	@Override
	public void setRecordingLength(BaseRecordingLengthBean<?> e) {
		this.recordingLength=e;
	}

	@Override
	public BaseRecordingLengthBean<?> getRecordingLength() {
		return recordingLength;
	}

	public BaseRecordingCommentBean<?> getRecordingComment() {
		return recordingComment;
	}

	public void setRecordingComment(BaseRecordingCommentBean<?> recordingComment) {
		this.recordingComment = recordingComment;
	}

}
