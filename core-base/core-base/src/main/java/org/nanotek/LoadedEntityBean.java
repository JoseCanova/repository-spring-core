package org.nanotek;

import org.nanotek.opencsv.service.EntityClassAccessor;
import org.nanotek.opencsv.service.EntityValueAccessor;

@TagInterface
public interface LoadedEntityBean<LE extends Base<LE>> extends Base<LE>,
EntityClassAccessor<Class<?>> ,
EntityValueAccessor<Long> {
}
