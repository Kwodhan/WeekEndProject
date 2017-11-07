package fr.istic.taa.weekEndProject.model;
/**
 * Le site d'une activity avec une localisation
 * @author aferey
 *
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * a geographical site with activities 
 * @author aferey
 *
 */
@Entity
@Table(name = "SITE")
@JsonPropertyOrder({ "id", "name", "location", "activities","siteWeb" })
public class SiteActivity implements InterfaceEntity {
	private Long id;
	private Set<Activity> activities = new HashSet<Activity>();
	
	private Location location;
	
	private String name;
	
	private String siteWeb;

	public SiteActivity(String name) {
		super();
		this.name = name;
	}
	public SiteActivity(String name,String siteWeb) {
		super();
		this.name = name;
		this.siteWeb=siteWeb;
	}

	public SiteActivity() {

	}

	@ManyToMany
	@JoinTable(name = "SITE_ACTIVITY", joinColumns = @JoinColumn(name = "SITE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "ID"))
	@JsonIgnoreProperties("sites")
	@ElementCollection(fetch = FetchType.EAGER)
	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@ManyToOne
	@JoinColumn(name = "location", referencedColumnName = "ID")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 150)
	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

}
