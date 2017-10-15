package fr.istic.taa.weekEndProject.jpa;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "fr.istic.taa.weekEndProject.controller", "fr.istic.taa.weekEndProject.service" })
@EntityScan("fr.istic.taa.weekEndProject.model")
@EnableJpaRepositories("fr.istic.taa.weekEndProject.repository")
@ImportResource({"applicationContext.xml","spring-security.xml"})

public class JpaTest {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		File file = new File("src/main/resources/log4j2.xml");
		 
		// this will force a reconfiguration
		context.setConfigLocation(file.toURI());
		
	
		SpringApplication.run(JpaTest.class, args);

	}

}
