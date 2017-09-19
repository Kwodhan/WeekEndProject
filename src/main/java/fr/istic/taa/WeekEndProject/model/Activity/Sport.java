package fr.istic.taa.WeekEndProject.model.Activity;

import javax.persistence.Entity;

import fr.istic.taa.WeekEndProject.model.Meteo;

@Entity
public class Sport extends AbstractActivity {

	public Sport(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * return true if the person have the level to do the sport
	 * 
	 * @param mylevel
	 * @param levelRequired
	 * @return
	 */
	public  boolean hasLevel(String mylevel,Meteo meteo) {
		return true;
		
	}

}
