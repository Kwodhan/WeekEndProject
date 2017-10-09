package fr.istic.taa.weekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.weekEndProject.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Query("select a from Activity a left join fetch a.meteos m where a.id = :id")
	public Activity findById(@Param("id") Long id);

	@Query("select distinct a from Activity a where a.name = :name")
	public List<Activity> findByName(@Param("name") String name);
	
	@Query("select distinct a from Activity a left join fetch a.meteos m  where a.type = 'Sport'")
	public List<Activity> findAllSport();
	
	@Query("select distinct a from Activity a left join fetch a.meteos m where a.type = 'Leisure'")
	public List<Activity> findAllLeisure();
	
	@Query("select distinct a from Activity a left join fetch a.meteos m")
	public List<Activity> findAll();

}
