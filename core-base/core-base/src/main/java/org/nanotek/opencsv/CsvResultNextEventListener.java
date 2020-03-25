package org.nanotek.opencsv;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

import org.nanotek.opencsv.function.CsvResultPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Log event
@Service
public class CsvResultNextEventListener<K extends CsvResult<?,?>> implements PropertyChangeListener{
	
	@Autowired
	private CsvResultPredicate<CsvResult<?,?>> csvResultPredicate;

	private static Logger log = LoggerFactory.getLogger(CsvResultPredicate.class);
	public CsvResultNextEventListener() {}

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			Optional<CsvResult<?,?>> result = (Optional<CsvResult<?, ?>>) arg0.getNewValue();
			result.ifPresent(r -> log.debug("Result UUID" + result.get().withUUID().toString()));
			csvResultPredicate.test(result);
		}
}
