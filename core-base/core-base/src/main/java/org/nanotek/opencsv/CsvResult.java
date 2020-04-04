package org.nanotek.opencsv;

import java.util.Date;
import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.PredicateBase;
import org.nanotek.Result;
import org.nanotek.beans.csv.ArtistBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component(value="CsvResult")
@Qualifier(value="CsvResult")
@Scope(proxyMode = ScopedProxyMode.NO,scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
public class CsvResult<K extends BaseBean<K,ID> , ID extends BaseEntity<?,?>> extends Result<K,ID> {

	private static final long serialVersionUID = -5768604376039283739L;

	private Boolean valid = false;
	
	@Autowired
	CsvOnResultPredicate<K,ID> computePredicate;
	
	@Autowired
	Validator validator;
	
	public CsvResult() {
		super();
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getValid() {
		return valid;
	}
	
	public CsvResult(K immutable, ID id) {
		super(immutable, id);
	}

	public CsvResult(K immutable) {
		super(immutable);
	}

	
	@Override
	public <V> Optional<? super V> on() {
		return compute(computePredicate).map(id ->{
			return validator.validate(id, Default.class);
		}).map(s->{
			return s.size()>0?(this.valid=false):(this.valid=true);
		});
	}
	
	@Override
	public Optional<ID> compute(PredicateBase<K, ID> predicate) {
		return predicate.evaluate(immutable).map(id->{
			this.id = id;
			return id;
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
				return this.compareTo(theBase) == 0;}
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