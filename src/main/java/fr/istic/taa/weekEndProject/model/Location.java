package fr.istic.taa.weekEndProject.model;

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
@JsonPropertyOrder({ "id", "city","region","latitude","longitude", "users", "sites" })

public class Location implements InterfaceEntity{
	private Long id;
	
	private String city;

	private String region;
	
	private Float latitude;

	private Float longitude;
	
	private Set<User> users = new HashSet<User>();
	
	private Set<SiteActivity> sites = new HashSet<SiteActivity>();

	public Location(String name) {
		super();
		this.city = name;
	}

	public Location() {
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
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> Users) {
		this.users = Users;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Location.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Location other = (Location) obj;
		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId())) {
			return false;
		}
		if (this.getCity() != other.getCity()) {
			return false;
		}
		if (this.getRegion() != other.getRegion()) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.getCity() != null ? this.getCity().hashCode() : 0);
		hash = 52 * hash + (this.getRegion() != null ? this.getRegion().hashCode() : 0);
	

		return hash;
	}

}
