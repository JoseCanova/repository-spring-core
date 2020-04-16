package org.nanotek;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.opencsv.CsvOnResultPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

@Validated
public abstract class Result<K extends IdBase<K,ID> , ID extends IdBase<?,?>> implements BooleanBase<K,ID> {

	private static final long serialVersionUID = -307344888633306177L;
 
	protected K immutable;
	
	@NotNull
	protected ID id = null;

	public Result() {
	}
	
	public Result(K immutable) { 
		this.immutable = immutable;
	}

//	public Result(K immutable,ID id) { 
//		this.immutable = immutable;
//		this.id = id;
//	}
//	
	public K getImmutable() {
		return immutable;
	}

	public void setImmutable(K immutable) {
		this.immutable = immutable;
	}

	@Override
	public abstract Optional<ID> compute(PredicateBase<K, ID> predicate);
}
