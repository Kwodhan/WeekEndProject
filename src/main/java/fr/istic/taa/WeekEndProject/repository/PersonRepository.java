package fr.istic.taa.WeekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.WeekEndProject.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p where p.name = :name")
	public List<Person> findByName(@Param("name") String name);

	@Query("select p from Person p left join fetch p.homes h where p.name = :name")
	public List<Person> findByNameWithLocation(@Param("name") String name);

	@Query("select p from Person p left join fetch p.activities h where p.name = :name")
	public List<Person> findByNameWithActivities(@Param("name") String name);

	@Query("select p from Person p left join fetch p.homes h left join fetch p.activities where p.name = :name")
	public List<Person> findByNameWithAll(@Param("name") String name);

	@Query("select p from Person p left join fetch p.homes h left join fetch p.activities where p.id = :id")
	public Person findById(@Param("id") Long id);

}
