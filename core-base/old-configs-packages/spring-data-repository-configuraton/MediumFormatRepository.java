package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.MediumFormat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediumFormatRepository<K extends MediumFormat<K>>
extends BrainzBaseRepository<K>{
}
