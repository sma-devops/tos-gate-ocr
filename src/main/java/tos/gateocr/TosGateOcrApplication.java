package tos.gateocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("tos.gateocr.repository")
public class TosGateOcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(TosGateOcrApplication.class, args);
	}
}