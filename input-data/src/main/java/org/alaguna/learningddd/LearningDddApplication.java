package org.alaguna.learningddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages= {"org.alaguna.learningddd","org.alaguna.shared"})
public class LearningDddApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningDddApplication.class, args);
	}

}
