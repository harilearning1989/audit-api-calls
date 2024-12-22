package com.web.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.web.utils", // Specify the base package to scan
		excludeFilters = {
				@ComponentScan.Filter(
						type = FilterType.REGEX,
						pattern = "com.web.utils.repos.*" // Exclude the repository package
				),
				@ComponentScan.Filter(
						type = FilterType.REGEX,
						pattern = "com.web.utils.services.*" // Exclude the service package
				),
				@ComponentScan.Filter(
						type = FilterType.REGEX,
						pattern = "com.web.utils.entities.*" // Exclude the entity package
				)
		}
)
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

}
