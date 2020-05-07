package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseLabelsSetEntity;

public interface MutableReleaseLabelSetEntity<T> extends ReleaseLabelsSetEntity<T>{
void setReleaseLabels(T t);
}
