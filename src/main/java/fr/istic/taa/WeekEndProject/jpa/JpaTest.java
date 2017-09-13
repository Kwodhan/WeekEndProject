package fr.istic.taa.WeekEndProject.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.taa.WeekEndProject.domain.Person;
import fr.istic.taa.WeekEndProject.domain.Place;
import fr.istic.taa.WeekEndProject.domain.activity.Activity;
import fr.istic.taa.WeekEndProject.domain.activity.Beach;
import fr.istic.taa.WeekEndProject.domain.activity.Football;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		System.out.println("Hello");
		try {
			test.createActitity();
			test.createPersonn();
		} catch (Exception e) {

			e.printStackTrace();
		}
		tx.commit();

		// test.listPerson();

		manager.close();
		System.out.println(".. done");
	}

	private void createActitity() {

		

	}

	private void createPersonn() {

		Person p1 = new Person("Jakab Gipsz");
		Person p2 = new Person("Japtain Nemo");

		Place place1 = new Place("Begin");
		Place place2 = new Place("End");
		manager.persist(p1);
		manager.persist(p2);
		manager.persist(place1);
		manager.persist(place2);
		p1.addPlace(place1);
		p1.addPlace(place2);
		p2.addPlace(place1);
		
		Activity a1 = new Football();
		Activity a2 = new Beach();

		manager.persist(a1);
		manager.persist(a2);
		
		p1.addActivities(a1, "1");
		p1.addActivities(a2, "1");
		p2.addActivities(a1, "3");

	}

	private void listPerson() {
		List<Person> resultList = manager.createQuery("Select a From Person a", Person.class).getResultList();
		System.out.println("num of Person:" + resultList.size());
		for (Person next : resultList) {
			System.out.println("next employee: " + next);
		}
	}

}
