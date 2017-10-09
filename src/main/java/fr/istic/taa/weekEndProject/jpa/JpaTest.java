package fr.istic.taa.weekEndProject.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "fr.istic.taa.weekEndProject.controller", "fr.istic.taa.weekEndProject.service" })
@EntityScan("fr.istic.taa.weekEndProject.model")
@EnableJpaRepositories("fr.istic.taa.weekEndProject.repository")
@ImportResource({"applicationContext.xml","spring-security.xml"})

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(JpaTest.class, args);

	}

}
