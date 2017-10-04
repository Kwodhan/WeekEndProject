package fr.istic.taa.weekEndProject.model;
/**
 * Le site d'une activity avec une localisation
 * @author aferey
 *
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
/**
 * a geographical site with activities 
 * @author aferey
 *
 */
@Entity
@Table(name = "Site")
@JsonPropertyOrder({ "id", "name", "location", "activities" })
public class SiteActivity {
	private Long id;
	private Set<AbstractActivity> activities = new HashSet<AbstractActivity>();
	private Location location;
	private String name;

	public SiteActivity(String name) {
		super();
		this.name = name;
	}

	public SiteActivity() {

	}

	@ManyToMany
	@JoinTable(name = "SITE_ACTIVITY", joinColumns = @JoinColumn(name = "SITE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "ID"))
	@JsonIgnoreProperties("sites")
	public Set<AbstractActivity> getActivities() {
		return activities;
	}

	public void setActivities(Set<AbstractActivity> activities) {
		this.activities = activities;
	}

	@ManyToOne
	@JoinColumn(name = "SITE_ID", referencedColumnName = "ID")
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

}