package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.RecordingLengthEntity;

public interface MutableRecordingLengthEntity<T> extends RecordingLengthEntity<T>
{
	void setRecordingLength(T e);
}
