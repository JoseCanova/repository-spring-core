package org.nanotek.opencsv.function;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.nanotek.IdBase;
import org.nanotek.opencsv.CsvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvResultPredicate
<K extends CsvResult<?,?>> 
implements Predicate<Optional<K>>{

	private static Logger log = LoggerFactory.getLogger(CsvResultPredicate.class);

	@Autowired
	private Validator validator;

	public CsvResultPredicate() {
	}

	@Override
	public boolean test(Optional<K> value) {
		boolean test = false;
		log.debug("checking thread");
		if (value.isPresent()) {
			IdBase<?,?> i = value.get().getImmutable();
			Set<ConstraintViolation<IdBase<?,?>>> violations =validator.validate(i, Default.class) ;
			if (violations.size() > 0) {
				violations.stream().forEach(v -> log.debug(v.getRootBeanClass().getName()  + v.getPropertyPath() + "  " + v.getMessage() ));
				log.debug("CSV RESULT INVALID " + value.get().withUUID().toString());
			}else { 
				test = true; 
			}
		}
		return test;

	}

}
