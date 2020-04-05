package org.nanotek.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.nanotek.beans.entity.BrainzBaseEntity;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface BrainzKey {
	Class<? extends BrainzBaseEntity> entityClass();
	String pathName();
}
