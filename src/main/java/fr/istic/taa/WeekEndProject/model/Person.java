package fr.istic.taa.WeekEndProject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;

@Entity
public class Person {
	private Long id;

	private String name;

	private List<Location> homes = new ArrayList<Location>();

	private Map<AbstractActivity, String> activities = new HashMap<AbstractActivity, String>();

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "PERS_LOCATION", joinColumns = @JoinColumn(name = "PERS_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID"))
	@JsonIgnoreProperties("person")
	public List<Location> getHomes() {
		return homes;
	}

	public void setHomes(List<Location> homes) {
		this.homes = homes;
	}

	@ElementCollection()
	@CollectionTable(name = "ACT_LEVEL")
	@MapKeyJoinColumn(name = "ACT_ID")
	@Column(name = "LEVEL")
	public Map<AbstractActivity, String> getActivities() {
		return activities;
	}

	public void setActivities(Map<AbstractActivity, String> activities) {
		this.activities = activities;
	}

	public void addActivities(AbstractActivity act, String level) {

		this.activities.put(act, level);

	}

	public void addLocation(Location place) {
		if (!this.getHomes().contains(place)) {
			this.getHomes().add(place);
		}

	}

	public String toString() {
		return "my name is " + this.name;
	}

}
