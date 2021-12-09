package org.alaguna.input_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages= {"org.alaguna.input_data","org.alaguna.shared"})
public class InputDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputDataApplication.class, args);
	}

}
