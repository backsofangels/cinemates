package com.salvatore.cinemates;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	
	@Bean //TODO Debug-only method
	public CommandLineRunner cmdLineRunner(ApplicationContext context) {
		return args -> {
			System.out.println("Inspecting beans...");
			String[] beans = context.getBeanDefinitionNames();
			Arrays.sort(beans);
			for (String name: beans) {
				System.out.println("Found bean: " + name);
			}
		};
	}
}
