package fr.istic.taa.WeekEndProject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;

@Entity
public class Person {
	private Long id;

	private String name;

	private Set<Location> homes = new HashSet<Location>();

	private Set<AbstractActivity> activities = new HashSet<AbstractActivity>();

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

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "PERS_LOCATION", joinColumns = @JoinColumn(name = "PERS_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID"))
	@JsonIgnoreProperties("persons")
	public Set<Location> getHomes() {
		return homes;
	}

	public void setHomes(Set<Location> homes) {
		this.homes = homes;
	}

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "PERS_ACTIVITY", joinColumns = @JoinColumn(name = "PERS_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "ID"))
	@JsonIgnoreProperties("persons")
	public Set<AbstractActivity> getActivities() {
		return activities;
	}

	public void setActivities(Set<AbstractActivity> activities) {
		this.activities = activities;
	}

	public void addActivities(AbstractActivity act) {
		if (!this.getActivities().contains(act)) {
			this.getActivities().add(act);
		}

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
