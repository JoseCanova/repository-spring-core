package org.nanotek.beans.csv;

import java.util.Set;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseRecordingBean;
import org.nanotek.entities.BaseRecordingLengthBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingBean  
<K extends BaseBean<RecordingBean<K>,Recording<?>>> 
extends ProxyBase<RecordingBean<K>,Recording<?>>
implements BaseRecordingBean<RecordingBean<K>>{

	private static final long serialVersionUID = 2926594064752891481L;

	private BaseArtistCreditBean<?> artistCredit;
	private BaseRecordingLengthBean<?> recordingLength;
	
	
	public RecordingBean(Class<? extends Recording<?>> class1) {
		super(class1);
		postConstruct();
	}

	public  void postConstruct()
	{
		artistCredit = new ArtistCreditBean<>();
		recordingLength = new RecordingLengthBean<>();
		
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
	public void setTracks(Set<Track<?>> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Track<?>> getTracks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRecordingLength(BaseRecordingLengthBean<?> e) {
		this.recordingLength=e;
		
	}

	@Override
	public BaseRecordingLengthBean<?> getRecordingLength() {
		return recordingLength;
	}


	//	
	//	@NotNull
	//	private Long recordingId;
	//	@NotNull
	//	private String gid;
	//	@NotNull
	//	private String name;
	//	@NotNull
	//	private Long artistCredit;
	//    private Long length;
	//    private String comment;
	//    private Long editsPending;
	//    private String lastUpdated;
	//    private String  video;



}
