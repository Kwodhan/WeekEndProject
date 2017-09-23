package fr.istic.taa.WeekEndProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.model.SiteActivity;
import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.model.Activity.Loisir;
import fr.istic.taa.WeekEndProject.service.ActivityService;
import fr.istic.taa.WeekEndProject.service.LocationService;
import fr.istic.taa.WeekEndProject.service.SiteActivityService;
/**
 * TODO : 	- Delete Site --> not Delete Location and Activity 
 * 			- Delete Location --> Delete Site
 * 			- Delete Activity --> Delete Site
 * @author aferey
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class SiteWebServiceTest {
	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/site/";

	@Autowired
	private SiteActivityService siteService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	SiteActivity getSite;

	SiteActivity updateSite;

	Location updateLocation;

	AbstractActivity updateActivity;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getSite = new SiteActivity("SiteGet");
		getSite = siteService.create(getSite);

		updateSite = new SiteActivity("SiteUpdate");
		updateSite = siteService.create(updateSite);

		updateLocation = new Location("Location");
		updateLocation = locationService.create(updateLocation);

		updateActivity = new Loisir("Loisir");
		updateActivity = activityService.create(updateActivity);
	}

	/**
	 * Create a Site
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateSite() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
		// Assert.assertTrue(jsonResponse.contains("\"type\":\"Sport\""));
	}

	/**
	 * Get a Site
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSite() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + getSite.getId() + ",\"activity\":null,\"location\":null,\"name\":\""
				+ getSite.getName() + "\"}";
		Assert.assertEquals(expected, jsonResponse);

	}

	/**
	 * Update the name of a Site
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateSite() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updateSite.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateSite.getId() + ",\"activity\":null,\"location\":null,\"name\":\"" + maj
				+ "\"}";
		Assert.assertEquals(expected, jsonResponse);
	}

	/**
	 * Update the name and add a location on a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateSiteWithLocation() throws Exception {


		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updateSite.getId() + "/addLocation/" + updateLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateSite.getId() + ",\"activity\":" + "null"
				+ ",\"location\":{\"id\":" + updateLocation.getId() + ",\"name\":\"" + updateLocation.getName()
				+ "\"},\"name\":\""+updateSite.getName()+"\"}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponseGet = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updateSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponseGet);
	}

	/**
	 * Update the name and add a Activity on a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateSiteWithActivity() throws Exception {

		
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updateSite.getId() + "/addActivity/" + updateActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateSite.getId() + ",\"activity\":{\"id\":"+updateActivity.getId()+",\"meteos\":[],\"name\":\""+updateActivity.getName()+"\",\"type\":\"Loisir\"}"
				+ ",\"location\":null,\"name\":\""+updateSite.getName()+"\"}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponseGet = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updateSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponseGet);

	}

}
