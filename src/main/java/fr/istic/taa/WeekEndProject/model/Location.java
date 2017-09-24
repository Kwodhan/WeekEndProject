package fr.istic.taa.WeekEndProject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Une Localisation geographique 
 * 
 * @author aferey
 *
 */
@Entity
@JsonPropertyOrder({ "id", "city", "persons", "sites" })
public class Location {
	private Long id;
	
	private String city;

	private Set<Person> persons = new HashSet<Person>();
	
	private Set<SiteActivity> sites = new HashSet<SiteActivity>();

	public Location(String name) {
		super();
		this.city = name;
	}

	public Location() {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@ManyToMany(mappedBy = "homes", cascade = { CascadeType.MERGE })
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnoreProperties("homes")
	@JsonIgnore
	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public String toString() {
		return "Location : " + this.city;
	}
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("location")
	@JsonIgnore
	public Set<SiteActivity> getSites() {
		return sites;
	}

	public void setSites(Set<SiteActivity> sites) {
		this.sites = sites;
	}

	

}
