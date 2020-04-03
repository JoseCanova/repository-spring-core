package org.nanotek.opencsv.function;

import java.util.Optional;
import java.util.function.Predicate;

import org.nanotek.opencsv.CsvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CsvResultPredicate
<K extends CsvResult<?,?>> 
implements Predicate<Optional<K>>{

	private static Logger log = LoggerFactory.getLogger(CsvResultPredicate.class);

	public CsvResultPredicate() {
	}

	@Override
	public boolean test(Optional<K> value) {
		boolean test = true;
		log.debug("checking thread");
		value.ifPresent(v->{
			log.debug("CSV RESULT " + v.toString());
		});
		return test;
	}

}
