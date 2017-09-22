package fr.istic.taa.WeekEndProject.model.Activity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.istic.taa.WeekEndProject.model.Meteo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Activity")
public abstract class AbstractActivity {
	private Long id;

	/**
	 * List of good meteo for the Activity
	 */
	private List<Meteo> meteos = new ArrayList<Meteo>();

	private String name;

	public AbstractActivity(String name) {
		super();
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * return true if the meteo is good for the sport
	 * 
	 * @param meteo
	 * @return
	 */

	public boolean hasGoodMeteo(Meteo meteo) {
		return this.getMeteos().contains(meteo);
	}

	protected void addGoodMeteo(Meteo meteo) {
		this.getMeteos().add(meteo);
	}

	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "ACTIVITY_METEO")
	public List<Meteo> getMeteos() {
		return meteos;
	}

	public void setMeteos(List<Meteo> meteos) {
		this.meteos = meteos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public abstract String getType();

}
