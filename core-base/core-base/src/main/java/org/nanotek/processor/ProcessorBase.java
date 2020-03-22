package org.nanotek.processor;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface ProcessorBase<T1, T2 , R> {
	@NotNull R next();
	List<R> load (@NotNull Long count);
}
