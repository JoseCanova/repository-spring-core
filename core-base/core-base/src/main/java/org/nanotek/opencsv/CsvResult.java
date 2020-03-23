package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.BaseEntity;
import org.nanotek.PredicateBase;
import org.nanotek.Result;
import org.nanotek.beans.csv.BaseBean;

public class CsvResult<K extends BaseBean<K,ID> , ID extends BaseEntity<?,?>> extends Result<K,ID>{

	public CsvResult() {
		super();
	}

	public CsvResult(K immutable, ID id) {
		super(immutable, id);
	}

	public CsvResult(K immutable) {
		super(immutable);
	}

	@Override
	public Optional<ID> on(PredicateBase<K, ID> predicate) {
		return Optional.empty();
	}

	@Override
	public Optional<ID> compute(PredicateBase<K, ID> predicate) {
		return Optional.empty();
	}
	
}
