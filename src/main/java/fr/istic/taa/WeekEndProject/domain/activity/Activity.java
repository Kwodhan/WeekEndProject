package fr.istic.taa.WeekEndProject.domain.activity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import fr.istic.taa.WeekEndProject.domain.Meteo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Activity {
	private Long id;

	/**
	 * List of good meteo for the Activity
	 */
	
	private List<Meteo> meteos = new ArrayList<Meteo>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * return true if the person have the level to do the sport
	 * 
	 * @param mylevel
	 * @param levelRequired
	 * @return
	 */
	public abstract boolean hasLevel(String mylevel, String levelRequired);

	/**
	 * return true if the meteo is good for the sport
	 * 
	 * @param meteo
	 * @return
	 */
	
	public boolean hasGoodMeteo(Meteo meteo) {
		return this.meteos.contains(meteo);
	}

	protected void addGoodMeteo(Meteo meteo) {
		this.meteos.add(meteo);
	}
	@Enumerated(EnumType.STRING)
	@ElementCollection
	public List<Meteo> getMeteos() {
		return meteos;
	}

	public void setMeteos(List<Meteo> meteos) {
		this.meteos = meteos;
	}

}
