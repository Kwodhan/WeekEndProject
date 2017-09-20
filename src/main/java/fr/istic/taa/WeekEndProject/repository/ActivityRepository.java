package fr.istic.taa.WeekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;

@Repository
public interface ActivityRepository extends JpaRepository<AbstractActivity, Long> {

	@Query("select a from AbstractActivity a where a.id = :id")
	public AbstractActivity findById(@Param("id") Long name);

	@Query("select a from AbstractActivity a where a.name = :name")
	public List<AbstractActivity> findByName(@Param("name") String name);

}
