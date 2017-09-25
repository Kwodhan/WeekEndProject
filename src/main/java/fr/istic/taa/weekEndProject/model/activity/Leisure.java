package fr.istic.taa.weekEndProject.model.activity;

import javax.persistence.Entity;
import javax.persistence.Transient;

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
	public String getType() {
		// TODO Auto-generated method stub
		return "Leisure";
	}

}
