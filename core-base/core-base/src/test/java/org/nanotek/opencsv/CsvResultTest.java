package org.nanotek.opencsv;

import java.util.Optional;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.beans.csv.ArtistBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvResultTest {

	@Test
	void testCsvResult() {
		CsvResult<?,?> bean = new CsvResult<>(new ArtistBean());
		bean.computePredicate = new CsvOnResultPredicate();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		bean.validator  = factory.getValidator();
		Optional<?> opt = bean.on();
		System.out.println(opt.get());
	}
	
}
