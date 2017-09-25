package fr.istic.taa.weekEndProject.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.istic.taa.weekEndProject.controller", "fr.istic.taa.weekEndProject.service" })
@EntityScan("fr.istic.taa.weekEndProject.model")
@EnableJpaRepositories("fr.istic.taa.weekEndProject.repository")
@ImportResource({"applicationContext.xml","spring-security.xml","spring-servlet.xml"})

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	/*	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		PersonService personService = context.getBean(PersonService.class);

		personService.create(new Person("Phone"));
		
		System.out.println(personService.findById(new Long(1)));
		context.close();*/

		/*
		 * private void createActitity() {
		 * 
		 * }
		 * 
		 * private void createPersonn() {
		 * 
		 * Person p1 = new Person("Jakab Gipsz"); Person p2 = new
		 * Person("Japtain Nemo");
		 * 
		 * Location place1 = new Location("Begin"); Location place2 = new
		 * Location("End"); manager.persist(p1); manager.persist(p2);
		 * manager.persist(place1);
		 * 
		 * AbstractActivity a1 = new Sport("Salut"); AbstractActivity a2 = new
		 * Loisir("Beach");
		 * 
		 * manager.persist(a1); manager.persist(a2);
		 * 
		 * p1.addActivities(a1, "1"); p1.addActivities(a2, "1"); p2.addActivities(a1,
		 * "3");
		 */

		SpringApplication.run(JpaTest.class, args);

	}

}
