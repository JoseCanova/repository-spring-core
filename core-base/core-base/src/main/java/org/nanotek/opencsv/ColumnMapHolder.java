package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.Base;

public interface ColumnMapHolder<K extends Base<?>>{
     Optional<K> verifyConfiguration();
}
