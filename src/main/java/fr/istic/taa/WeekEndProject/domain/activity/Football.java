package fr.istic.taa.WeekEndProject.domain.activity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.istic.taa.WeekEndProject.domain.Meteo;

@Entity
@DiscriminatorValue("Football")
public class Football extends Activity {



	public Football() {
		super();
		this.addGoodMeteo(Meteo.Ensoleille);
		this.addGoodMeteo(Meteo.QuelqueNuage);
		this.addGoodMeteo(Meteo.Nuageux);
		this.addGoodMeteo(Meteo.FaibleAverse);
	}

	@Override
	public boolean hasLevel(String mylevel, String levelRequired) {
		// TODO Auto-generated method stub
		return false;
	}

}
