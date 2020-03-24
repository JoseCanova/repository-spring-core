package org.nanotek.opencsv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseEntity;
import org.nanotek.PredicateBase;
import org.nanotek.Result;
import org.nanotek.beans.csv.BaseBean;

public class CsvResult<K extends BaseBean<K,ID> , ID extends BaseEntity<?,?>> extends Result<K,ID>{

	private static final long serialVersionUID = -5768604376039283739L;

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

	@Override
	public ID getId() {
		return id;
	}
	
	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = CsvResult.class.cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}}