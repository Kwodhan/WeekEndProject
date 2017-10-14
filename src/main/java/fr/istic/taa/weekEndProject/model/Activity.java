package fr.istic.taa.weekEndProject.model;

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
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Une activity
 * 
 * @author aferey
 *
 */
@Entity
@JsonPropertyOrder({ "type", "id", "name", "meteos", "users", "sites" })
public class Activity implements InterfaceEntity {
	private Long id;

	/**
	 * List of good meteo for the Activity
	 */
	private Set<Meteo> meteos = new HashSet<Meteo>();

	private String name;

	private String type;

	private Set<SiteActivity> sites = new HashSet<SiteActivity>();

	private Set<User> users = new HashSet<User>();

	public Activity(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Activity() {

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
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Activity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Activity other = (Activity) obj;
		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId())) {
			return false;
		}
		if (this.getName() != other.getName()) {
			return false;
		}
		if (this.getType() != other.getType()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
		hash = 52 * hash + (this.getType() != null ? this.getType().hashCode() : 0);
	

		return hash;
	}

}
