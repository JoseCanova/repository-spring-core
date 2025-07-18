package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvFileItemConcreteStrategy2Test {

	@Autowired
	ApplicationContext context;
	
	
	@SuppressWarnings("rawtypes")
	@Test
	void CsvFileItemConcreteStrategy2() {
		assertNotNull(context);
		Map<String, CsvFileItemConcreteStrategy2> theBeans = context.getBeansOfType(CsvFileItemConcreteStrategy2.class);	
		assertNotNull(theBeans);
	}

}
