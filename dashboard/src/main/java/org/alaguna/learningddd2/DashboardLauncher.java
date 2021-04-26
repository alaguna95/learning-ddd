package org.alaguna.learningddd2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.alaguna.learningddd2")
public class DashboardLauncher {

	public static void main(String[] args) {
		SpringApplication.run(DashboardLauncher.class, args);
	}

}
