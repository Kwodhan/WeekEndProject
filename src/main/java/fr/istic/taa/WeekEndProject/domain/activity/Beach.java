package fr.istic.taa.WeekEndProject.domain.activity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.istic.taa.WeekEndProject.domain.Meteo;

@Entity
@DiscriminatorValue("Beach")
public class Beach extends Activity {

	public Beach() {
		super();
		this.addGoodMeteo(Meteo.Ensoleille);
		this.addGoodMeteo(Meteo.QuelqueNuage);
	}

	@Override
	public boolean hasLevel(String mylevel, String levelRequired) {
		// TODO Auto-generated method stub
		return true;
	}

}
