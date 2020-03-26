package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface EndMonthEntity<T extends Serializable> {
   T getEndMonth();
}
