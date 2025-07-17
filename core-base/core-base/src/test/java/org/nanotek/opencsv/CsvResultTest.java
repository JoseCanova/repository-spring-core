package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.beans.csv.ArtistBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvResultTest {

	@Autowired
	ApplicationContext context;
	
	@Test
	void testCsvResult() {
		assertNotNull(context);
		CsvResult<?,?> bean = new CsvResult<>(new ArtistBean());
		bean.computePredicate = new CsvOnResultPredicate(context);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		bean.validator  = factory.getValidator();
		Optional<?> opt = bean.on();
		System.out.println(opt.get());
	}
	
}
