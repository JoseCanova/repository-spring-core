package org.nanotek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;

@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
@EnableIntegrationManagement
@EnableAutoConfiguration(exclude={RabbitAutoConfiguration.class})
@EnableJdbcRepositories(basePackages = {"org.nanotek.repository.jdbc"})
@EnableJpaRepositories(basePackages = {"org.nanotek.repository.jpa"})
public class App {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public App() {
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
