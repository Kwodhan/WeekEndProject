package fr.istic.taa.weekEndProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.model.activity.Leisure;
import fr.istic.taa.weekEndProject.service.ActivityService;
import fr.istic.taa.weekEndProject.service.LocationService;
import fr.istic.taa.weekEndProject.service.SiteActivityService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;

/**
 * 
 * 
 * @author aferey
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class SiteWebServiceTest {
	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/sites/";

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

	SiteActivity deleteSite;

	Location updateLocation;

	AbstractActivity updateActivity;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getSite = new SiteActivity("SiteGet");
		getSite = siteService.create(getSite);

		updateSite = new SiteActivity("SiteUpdate");
		updateSite = siteService.create(updateSite);

		deleteSite = new SiteActivity("SiteDelete");
		deleteSite = siteService.create(deleteSite);

		updateLocation = new Location("Location");
		updateLocation = locationService.create(updateLocation);

		updateActivity = new Leisure("Leisure");
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
				.perform(get(SERVICE_URI + getSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + getSite.getId() +
		// ",\"name\":\""+getSite.getName()+"\",\"location\":null,\"activity\":}";
		String expected = FactoryJSON.Site(getSite.getId(), getSite.getName());
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
		String payload = FactoryJSON.Site(updateSite.getId(), maj);
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = FactoryJSON.Site(updateSite.getId(), maj);
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
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + updateSite.getId() + ",\"name\":\"" +
		// updateSite.getName()
		// + "\",\"location\":{\"id\":" + updateLocation.getId() + ",\"city\":\"" +
		// updateLocation.getCity()
		// + "\"}" + ",\"activity\":" + "null" + "}";
		String expected = FactoryJSON.Site(updateSite.getId(), updateSite.getName(), updateLocation);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponseGet = this.mockMvc
				.perform(get(SERVICE_URI + updateSite.getId()).contentType(MediaType.APPLICATION_JSON)
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
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + updateSite.getId() + ",\"name\":\"" +
		// updateSite.getName()
		// + "\",\"location\":null" + ",\"activity\":{\"id\":" + updateActivity.getId()
		// + ",\"name\":\""
		// + this.updateActivity.getName() + "\",\"type\":\"Leisure\",\"meteos\":[]}}";
		String expected = FactoryJSON.Site(updateSite.getId(), updateSite.getName(), updateActivity);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponseGet = this.mockMvc
				.perform(get(SERVICE_URI + updateSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertEquals(expected, jsonResponseGet);

	}

	/**
	 * Delete a Site and verify that Location and Activity exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteSiteWithActivityAndLocation() throws Exception {

		String jsonResponseActivity = this.mockMvc
				.perform(put(SERVICE_URI + deleteSite.getId() + "/addActivity/" + updateActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expectedActivity = FactoryJSON.Site(deleteSite.getId(), deleteSite.getName(), updateActivity);
		Assert.assertEquals(expectedActivity, jsonResponseActivity);

		String jsonResponseGetActivity = this.mockMvc
				.perform(get(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertEquals(expectedActivity, jsonResponseGetActivity);

		String jsonResponseLocation = this.mockMvc
				.perform(put(SERVICE_URI + deleteSite.getId() + "/addLocation/" + updateLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expectedLocation = FactoryJSON.Site(deleteSite.getId(), deleteSite.getName(), updateLocation,
				updateActivity);
		Assert.assertEquals(expectedLocation, jsonResponseLocation);

		String jsonResponseGetLocation = this.mockMvc
				.perform(get(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertEquals(expectedLocation, jsonResponseGetLocation);

		this.mockMvc
				.perform(delete(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String jsonResponseVerifLoc = this.mockMvc
				.perform(get("/locations/" + updateLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expectedLoc = FactoryJSON.Location(updateLocation.getId(), updateLocation.getCity());
		Assert.assertEquals(expectedLoc, jsonResponseVerifLoc);

		String jsonResponseVerifActi = this.mockMvc
				.perform(get("/activities/" + updateActivity.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expectedAct = FactoryJSON.Activity(updateActivity.getId(), updateActivity.getName(),
				updateActivity.getType());
		Assert.assertEquals(expectedAct, jsonResponseVerifActi);

	}

	/**
	 * Delete a Activity and verify that Site doesn't exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteActivitySiteDontExist() throws Exception {
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + deleteSite.getId() + "/addActivity/" + updateActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = FactoryJSON.Site(deleteSite.getId(), deleteSite.getName(), updateActivity);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponseGet = this.mockMvc
				.perform(get(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertEquals(expected, jsonResponseGet);

		this.mockMvc
				.perform(delete("/activities/" + updateActivity.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get("/activities/" + updateActivity.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
		try {

			activityService.findById(updateActivity.getId());
			Assert.assertFalse("ActivityNotFound expected", true);
		} catch (ActivityNotFound e) {
			Assert.assertTrue("LocationNotFound", true);
		}

		this.mockMvc
				.perform(get(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

	}

	/**
	 * Delete a Activity and verify that Site doesn't exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteLocationSiteDontExist() throws Exception {
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + deleteSite.getId() + "/addLocation/" + updateLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = FactoryJSON.Site(deleteSite.getId(), deleteSite.getName(), updateLocation);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponseGet = this.mockMvc
				.perform(get(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertEquals(expected, jsonResponseGet);

		this.mockMvc
				.perform(delete("/locations/" + updateLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get("/locations/" + updateLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
		try {

			locationService.findById(updateLocation.getId());
			Assert.assertFalse("LocationsNotFound expected", true);
		} catch (LocationNotFound e) {
			Assert.assertTrue("LocationNotFound", true);
		}

		this.mockMvc
				.perform(get(SERVICE_URI + deleteSite.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

	}

}
