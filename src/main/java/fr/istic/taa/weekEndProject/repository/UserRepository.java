package fr.istic.taa.weekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.weekEndProject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select p from User p where p.firstName = :firstName")
	public List<User> findByName(@Param("firstName") String firstName);

	@Query("select p from User p left join fetch p.homes h  where p.firstName = :firstName")
	public List<User> findByNameWithAll(@Param("firstName") String firstName);

	@Query("select p from User p left join fetch p.roles left join fetch p.homes h left join fetch p.activities a where p.id = :id")
	public User findById(@Param("id") Long id);
	
	@Query("select p from User p left join fetch p.roles r left join fetch p.homes h left join fetch p.activities a where p.pseudo = :pseudo")
	public List<User> findByPseudo(@Param("pseudo") String pseudo);
	
	
	
	

}
