package fr.istic.taa.weekEndProject.model.activity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Leisure extends AbstractActivity {

	public Leisure(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Leisure() {
		super("");
	}

	@Override
	@Transient
	@JsonIgnore
	public String getType() {
		// TODO Auto-generated method stub
		return "Leisure";
	}

}
