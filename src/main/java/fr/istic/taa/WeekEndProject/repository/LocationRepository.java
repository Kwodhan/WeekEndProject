package fr.istic.taa.WeekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.WeekEndProject.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query("select p from Location p where p.id = :id")
	public Location findById(@Param("id") Long id);

	@Query("select p from Location p where p.name = :name")
	public List<Location> findByName(@Param("name") String name);

	@Query("select p from Location p left join p.person where p.name = :name")
	public List<Location> findByNameWithPerson(@Param("name") String name);
}
