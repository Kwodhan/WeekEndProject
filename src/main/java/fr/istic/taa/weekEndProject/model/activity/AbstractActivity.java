package fr.istic.taa.weekEndProject.model.activity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.istic.taa.weekEndProject.model.Meteo;
import fr.istic.taa.weekEndProject.model.Person;
import fr.istic.taa.weekEndProject.model.SiteActivity;

/**
 * Une activity
 * 
 * @author aferey
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Activity")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Sport.class, name = "Sport"), @Type(value = Leisure.class, name = "Leisure") })
@JsonPropertyOrder({ "id", "name", "type", "meteos", "persons", "sites" })
public abstract class AbstractActivity {
	private Long id;

	/**
	 * List of good meteo for the Activity
	 */
	private Set<Meteo> meteos = new HashSet<Meteo>();

	private String name;

	private Set<SiteActivity> sites = new HashSet<SiteActivity>();

	private Set<Person> persons = new HashSet<Person>();

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
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ACTIVITY_METEO")
	public Set<Meteo> getMeteos() {
		return meteos;
	}

	public void setMeteos(Set<Meteo> meteos) {
		this.meteos = meteos;
	}

	@JsonUnwrapped
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "activities")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnoreProperties("activities")
	@JsonIgnore
	public Set<SiteActivity> getSites() {
		return sites;
	}

	public void setSites(Set<SiteActivity> sites) {
		this.sites = sites;
	}

	@ManyToMany(mappedBy = "activities", cascade = { CascadeType.MERGE })
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnoreProperties("activities")
	@JsonIgnore
	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@JsonIgnore
	public abstract String getType();

	public void setType(String type) {

	}

}
