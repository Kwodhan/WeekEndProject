package fr.istic.taa.weekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.weekEndProject.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query("select l from Location l left join l.persons p left join l.sites s where l.id = :id")
	public Location findById(@Param("id") Long id);

	@Query("select l from Location l where l.city = :city")
	public List<Location> findByName(@Param("city") String city);

	@Query("select l from Location l left join l.persons p where l.city = :city")
	public List<Location> findByNameWithPerson(@Param("city") String city);
}
