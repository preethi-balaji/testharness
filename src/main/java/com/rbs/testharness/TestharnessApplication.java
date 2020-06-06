package com.rbs.testharness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TestharnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestharnessApplication.class, args);
	}

}
