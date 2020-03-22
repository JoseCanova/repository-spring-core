package org.nanotek;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.validation.annotation.Validated;

@FunctionalInterface
public interface Mediator<T> {
	@Validated(value = Default.class)
	void mediate(@Valid T t);
}
