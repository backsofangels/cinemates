package com.salvatore.cinemates;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	private final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner cmdLineRunner(ApplicationContext context) {
		return args -> {
			logger.debug("Inspecting beans...");
			String[] beans = context.getBeanDefinitionNames();
			Arrays.sort(beans);
			for (String name: beans) {
				logger.debug(String.format("Found bean: %s", name));
			}
		};
	}
}
