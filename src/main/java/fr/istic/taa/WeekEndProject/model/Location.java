package fr.istic.taa.WeekEndProject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Place 
 * 
 * @author aferey
 *
 */
@Entity
public class Location {
	private Long id;

	private String name;

	private List<Person> person = new ArrayList<Person>();

	public Location(String name) {
		super();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "homes",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH})
	@JsonBackReference
	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}
	
	public String toString() {
		return "Location : "+ this.name;
	}

}
