package org.nanotek.entities;

import org.nanotek.entities.immutables.IsoCode1Entity;

public interface MutableIsoCode1Entity<T>  extends IsoCode1Entity<T>{
void setIsoCode1(T  t);
}
