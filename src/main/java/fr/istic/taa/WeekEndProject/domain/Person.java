package fr.istic.taa.WeekEndProject.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import fr.istic.taa.WeekEndProject.domain.activity.Activity;

@Entity
public class Person {
	private Long id;

	private String name;

	private List<Place> homes = new ArrayList<Place>();

	private Map<Activity, String> activities = new HashMap<Activity, String>();

	public Person(String name) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "PERS_PLACE", joinColumns = @JoinColumn(name = "PERS_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PLACE_ID", referencedColumnName = "ID"))
	public List<Place> getHomes() {
		return homes;
	}

	public void setHomes(List<Place> homes) {
		this.homes = homes;
	}

	@ElementCollection
	@CollectionTable(name = "ACT_LEVEL")
	@MapKeyJoinColumn(name = "ACT_ID")
	@Column(name = "LEVEL")
	public Map<Activity, String> getActivities() {
		return activities;
	}

	public void setActivities(Map<Activity, String> activities) {
		this.activities = activities;
	}

	public void addActivities(Activity act, String level) {

		this.activities.put(act, level);

	}

	public void addPlace(Place place) {
		if (!homes.contains(place)) {
			this.homes.add(place);
		}

	}

}
