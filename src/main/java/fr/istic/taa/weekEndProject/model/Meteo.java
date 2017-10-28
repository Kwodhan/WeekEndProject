package fr.istic.taa.weekEndProject.model;
/*
 * 
 * 
 * list of reponse from :
 * https://www.prevision-meteo.ch/services/json/Rennes
 * 
 */
public enum Meteo {
	ensoleille("Ensoleillé"),
	nuit_claire("Nuit claire"),
	ciel_voile("Ciel voilé"),
	nuit_legerement_voilee("Ciel voilé"),
	faibles_passages_nuageux("Faible passages nuageux"),
	nuit_bien_degagee("Dégagée"),
	brouillard("Brouillard"),
	stratus("Stratus"),
	stratus_se_dissipant("Stratus"),
	nuit_claire_et_stratus("Nuit claire"),
	eclaircies("Eclaircies"),
	nuit_nuageuse("Nuageux"),
	faiblement_nuageux("Nuageux"),
	fortement_nuageux("Nuageux"),
	averses_de_pluie_faible("Pluie faible"),
	nuit_avec_averses("Pluie"),
	averses_de_pluie_moderee("Pluie"),
	averses_de_pluie_forte("Pluie forte"),
	couvert_avec_averses("Pluie"),
	pluie_faible("Pluie"),
	pluie_forte("Pluie forte"),
	pluie_moderee("Pluie"),
	developpement_nuageux("Nuageux"),
	nuit_avec_developpement_nuageux("Nuageux"),
	faiblement_orageux("Orageux"),
	nuit_faiblement_orageuse("Orageux"),
	orage_modere("Orageux"),
	fortement_orageux("Orageux"),
	averses_de_neige_faible("Neige"),
	nuit_avec_averses_de_neige_faible("Neige"),
	neige_faible("Neige"),
	neige_moderee("Neige"),
	neige_forte("Neige forte"),
	pluie_et_neige_melee_faible("Pluie et Neige"),
	pluie_et_neige_melee_moderee("Pluie et Neige"),
	pluie_et_neige_melee_forte("Pluie et Neige");
	
	private String name;
	
	private Meteo(String name) {
		this.name=name;
	}
	public String getMeteo() {
		return this.name;
	}

}
