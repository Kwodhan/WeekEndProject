package fr.istic.taa.weekEndProject.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.SiteActivityNotFound;

public interface SiteActivityService {
	public SiteActivity create(SiteActivity site);

	public SiteActivity delete(Long id) throws SiteActivityNotFound;

	public List<SiteActivity> findAll();

	public SiteActivity update(SiteActivity activity) throws SiteActivityNotFound;
	
	public SiteActivity updateActivities(Long id, Set<Activity> activities) throws ActivityNotFound, SiteActivityNotFound;

	public SiteActivity updateActivities(Long id, Long idAct) throws ActivityNotFound, SiteActivityNotFound;
	
	public SiteActivity deleteActivities(Long id, Long idAct) throws ActivityNotFound, SiteActivityNotFound;
	
	public SiteActivity findById(Long id) throws SiteActivityNotFound;

	public List<SiteActivity> findByName(String name);
	
	public List<SiteActivity> findByLocationAndActivity(@Param("ild") Long idl,@Param("ida") Long ida);
}
