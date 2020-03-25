package org.nanotek.processor;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface ProcessorBase<R> {
	@NotNull R getNext();
	List<R> load (@NotNull Long count);
}
