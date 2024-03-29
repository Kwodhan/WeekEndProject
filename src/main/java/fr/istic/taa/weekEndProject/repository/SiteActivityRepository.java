package fr.istic.taa.weekEndProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.istic.taa.weekEndProject.model.SiteActivity;

@Repository
public interface SiteActivityRepository extends JpaRepository<SiteActivity, Long> {

	@Query("select s from SiteActivity s left join fetch s.location l left join fetch s.activities a where s.id = :id")
	public SiteActivity findById(@Param("id") Long id);

	@Query("select distinct s from SiteActivity s where s.name = :name")
	public List<SiteActivity> findByName(@Param("name") String name);

	@Query("select distinct s from SiteActivity s left join fetch s.location l left join fetch s.activities a ")
	public List<SiteActivity> findAll();
	
	@Query("select distinct s from SiteActivity s left join fetch s.location l left join fetch s.activities a where l.id = :idl and a.id = :ida ")
	public List<SiteActivity> findByLocationAndActivity(@Param("idl") Long idl,@Param("ida") Long ida);
	
}
