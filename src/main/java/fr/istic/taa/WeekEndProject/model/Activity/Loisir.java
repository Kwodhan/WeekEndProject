package fr.istic.taa.WeekEndProject.model.Activity;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Loisir extends AbstractActivity {

	public Loisir(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Loisir() {
		super("");
	}

	@Override
	@Transient
	public String getType() {
		// TODO Auto-generated method stub
		return "Loisir";
	}

}
