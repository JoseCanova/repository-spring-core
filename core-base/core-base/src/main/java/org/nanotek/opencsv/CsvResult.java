package org.nanotek.opencsv;

import java.util.Date;
import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.PredicateBase;
import org.nanotek.Result;
import org.nanotek.beans.csv.ArtistBean;
import org.springframework.context.ApplicationContext;

public class CsvResult<K extends BaseBean<K,ID> , ID extends BaseEntity<?,?>> extends Result<K,ID> {

	private static final long serialVersionUID = -5768604376039283739L;

	private Boolean valid = false;
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	CsvOnResultPredicate<K,ID> computePredicate;
	
	Validator validator;

	private ApplicationContext applicationContext;
	
	public CsvResult() {
		super();
		postConstruct();
	}

	private void postConstruct() {
		computePredicate= new CsvOnResultPredicate<>(applicationContext);
		validator = factory.getValidator();
	}
	
	public CsvResult(K immutable, ApplicationContext applicationContext) {
		super(immutable);
		this.applicationContext = applicationContext;
		postConstruct();
	}

	public CsvResult(K immutable) {
		super(immutable);
		postConstruct();
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean isValid() {
		return valid;
	}
	
	
	@Override
	public <V> Optional<? super V> on() {
		return compute(computePredicate).map(id ->{
			return validator.validate(id, CsvValidationGroup.class);
		}).map(s->{
			return s.size()>0?(this.valid=false):(this.valid=true);
		});
	}
	
	@Override
	public Optional<ID> compute(PredicateBase<K, ID> predicate) {
		return predicate.evaluate(immutable).map(id->{
			return (this.id = id);
		});
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
				return this.compareTo((K) theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return getId().md5Digest().hashCode();
	}

	@Override
	public String toString() {
		return "Result in " + new Date().toString();
	}
	
	public static void main(String[] args) {
		CsvResult<?,?> bean = new CsvResult<>(new ArtistBean());
		bean.computePredicate = new CsvOnResultPredicate();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		bean.validator  = factory.getValidator();
		Optional<?> opt = bean.on();
		System.out.println(opt.get());
	} 

}