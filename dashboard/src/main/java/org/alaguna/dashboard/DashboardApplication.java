package org.alaguna.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"org.alaguna.dashboard","org.alaguna.shared"})
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

}
