package org.nanotek;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.validation.annotation.Validated;

@Validated
@FunctionalInterface
public interface Transformer<I, R> {
	@Validated(value = Default.class) @Valid R transform(@Validated(value = Default.class) @Valid I i);
}
