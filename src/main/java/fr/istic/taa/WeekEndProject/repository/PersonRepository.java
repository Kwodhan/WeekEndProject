package fr.istic.taa.WeekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.WeekEndProject.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p where p.firstName = :firstName")
	public List<Person> findByName(@Param("firstName") String firstName);

	@Query("select p from Person p left join fetch p.homes h  where p.firstName = :firstName")
	public List<Person> findByNameWithAll(@Param("firstName") String firstName);

	@Query("select p from Person p left join fetch p.homes h left join fetch p.activities a where p.id = :id")
	public Person findById(@Param("id") Long id);
	
	
	
	

}
