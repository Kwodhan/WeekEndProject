package fr.istic.taa.WeekEndProject.model.Activity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import fr.istic.taa.WeekEndProject.model.Meteo;

@Entity
public class Sport extends AbstractActivity {

	
	
	public Sport(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Sport() {
		super("");
	}

	/**
	 * return true if the person have the level to do the sport
	 * 
	 * @param mylevel
	 * @param levelRequired
	 * @return
	 */
	public boolean hasLevel(String mylevel, Meteo meteo) {
		return true;

	}

	@Override
	@Transient
	public String getType() {
		// TODO Auto-generated method stub
		return "Sport";
	}
	
	

}
