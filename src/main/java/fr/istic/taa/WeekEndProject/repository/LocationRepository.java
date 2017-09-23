package fr.istic.taa.WeekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.WeekEndProject.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query("select l from Location l left join l.persons p left join l.sites s where l.id = :id")
	public Location findById(@Param("id") Long id);

	@Query("select l from Location l where l.name = :name")
	public List<Location> findByName(@Param("name") String name);

	@Query("select l from Location l left join l.persons p where l.name = :name")
	public List<Location> findByNameWithPerson(@Param("name") String name);
}
