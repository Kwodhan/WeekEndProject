package fr.istic.taa.weekEndProject.model;

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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;

@Entity
@JsonPropertyOrder({ "id", "firstName", "lastName", "emailAddress", "homes", "activities" })
public class Person {
	private Long id;

	private String firstName;
	private String lastName;
	private String emailAddress;

	private Set<Location> homes = new HashSet<Location>();

	private Set<AbstractActivity> activities = new HashSet<AbstractActivity>();

	

	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	

	

	

}
