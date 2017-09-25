package fr.istic.taa.weekEndProject.model;
/**
 * Le site d'une activity avec une localisation
 * @author aferey
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;

@Entity
@JsonPropertyOrder({ "id", "name", "location", "activity" })
public class SiteActivity {
	private Long id;
	private AbstractActivity activity;
	private Location location;
	private String name;

	public SiteActivity(String name) {
		super();
		this.name = name;
	}

	public SiteActivity() {

	}

	@ManyToOne
	@JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "ID")
	public AbstractActivity getActivity() {
		return activity;
	}

	public void setActivity(AbstractActivity activity) {
		this.activity = activity;
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

}
