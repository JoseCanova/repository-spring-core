package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.beans.csv.BaseBean;

public interface ColumnMapHolder<K extends BaseBean<?,?>>{
     Optional<K> verifyConfiguration();
}
