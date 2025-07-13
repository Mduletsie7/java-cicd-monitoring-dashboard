package mdumisi.dev.cicd_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CicdDashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(CicdDashboardApplication.class, args);
	}
}
