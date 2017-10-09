package fr.istic.taa.weekEndProject.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Meteo;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.exception.MeteoNotFound;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private SiteActivityService siteService;

	public void sendEmailRegister(User person) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(person.getEmailAddress());
		helper.setText("Bienvenue " + person.getFirstName() + " dans WeekEndProject");

		helper.setSubject("Bienvenue " + person.getFirstName() + " dans WeekEndProject");

		sender.send(message);
	}

	public void sendEmailNotification(User person) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		List<SiteActivity> sites = siteService.findAll();
		helper.setTo(person.getEmailAddress());
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 3);
		dt = c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		dateFormat.format(dt);
		helper.setSubject("Notification pour le Week end du " + dateFormat.format(dt));
		String begin = "Bonjour, " + person.getFirstName() + "\nVoici les activitées disponible pour le week-end du "
				+ dateFormat.format(dt) + " .\n";
		String corp = "";
		String corpSamedi = "Samedi : \n";
		String corpDimanche = "Dimanche : \n";
		boolean send = false;
		for (SiteActivity site : sites) {

			for (Activity activity : site.getActivities()) {
				if (person.getActivities().contains(activity) && person.getHomes().contains(site.getLocation())) {
					send = true;
					try {
						Meteo sam = Meteo.valueOf(callMeteoSaturday(site.getLocation().getCity()).replaceAll("-", "_"));
						corpSamedi = corpSamedi + "Le temps sera : " + sam.getMeteo() + ".\n";
						corpSamedi = corpSamedi + "Vous pouvez aller à " + site.getLocation().getCity()
								+ " pour faire du " + activity.getName() + ".\n";

						if (!activity.hasGoodMeteo(sam) && activity.getType().equals("Sport")) {
							corpSamedi = corpSamedi
									+ "Attention Il faut avoir une certaine expérience pour pratiquer ce sport avec ce temps\n";
						}
					} catch (MeteoNotFound e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						Meteo dim = Meteo.valueOf(callMeteoSunday(site.getLocation().getCity()).replaceAll("-", "_"));
						corpDimanche = corpDimanche + "Le temps sera : " + dim.getMeteo() + ".\n";
						corpDimanche = corpDimanche + "Vous pouvez aller à " + site.getLocation().getCity()
								+ " pour faire du " + activity.getName() + ".\n";
						if (!activity.hasGoodMeteo(dim) && activity.getType().equals("Sport")) {
							corpDimanche = corpDimanche
									+ "Attention Il faut avoir une certaine expérience pour pratiquer ce sport avec ce temps\n";
						}
					} catch (MeteoNotFound e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}
		corp = corpSamedi + "\n" + corpDimanche;
		String end = "Passez un bon Week-end!\n A la semaine prochaine!!";

		helper.setText(begin + corp + end);

		if (send) {
			sender.send(message);
		}
	}

	/**
	 * 
	 * @param city
	 *            La ville ou l'on veut regarder la meteo
	 * @return
	 */
	private String callMeteoSaturday(String city) throws MeteoNotFound {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("https://www.prevision-meteo.ch/services/json/" + city);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			JSONObject obj = new JSONObject(response.getEntity(String.class));

			String condition = obj.getJSONObject("fcst_day_3").getString("condition_key");

			return condition;

		} catch (ClientHandlerException | UniformInterfaceException | JSONException e) {
			throw new MeteoNotFound();
		}
	}

	private String callMeteoSunday(String city) throws MeteoNotFound {

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("https://www.prevision-meteo.ch/services/json/" + city);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			JSONObject obj = new JSONObject(response.getEntity(String.class));

			String condition = obj.getJSONObject("fcst_day_4").getString("condition_key");

			return condition;

		} catch (ClientHandlerException | UniformInterfaceException | JSONException e) {
			throw new MeteoNotFound();
		}
	}
}
