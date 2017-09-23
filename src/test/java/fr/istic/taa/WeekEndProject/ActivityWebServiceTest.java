package fr.istic.taa.WeekEndProject;

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

import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.model.Activity.Loisir;
import fr.istic.taa.WeekEndProject.model.Activity.Sport;
import fr.istic.taa.WeekEndProject.service.ActivityService;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class ActivityWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/activity/";

	@Autowired
	private ActivityService activityService;


	private AbstractActivity getSport;

	private AbstractActivity updateSport;

	private AbstractActivity deleteSport;

	private AbstractActivity createSport;
	
	private AbstractActivity getLoisir;

	private AbstractActivity updateLoisir;

	private AbstractActivity deleteLoisir;

	private AbstractActivity createLoisir;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		//Sport
		getSport = new Sport("Name");
		getSport = activityService.create(getSport);

		updateSport = new Sport("dsq");
		updateSport = activityService.create(updateSport);

		createSport = new Sport("qds");
		createSport = activityService.create(createSport);

		deleteSport = new Sport("cxw");
		deleteSport = activityService.create(deleteSport);
		
		//Loisir
		getLoisir = new Loisir("Name");
		getLoisir = activityService.create(getLoisir);

		updateLoisir = new Loisir("dsq");
		updateLoisir = activityService.create(updateLoisir);

		createLoisir = new Loisir("qds");
		createLoisir = activityService.create(createLoisir);

		deleteLoisir = new Loisir("cxw");
		deleteLoisir = activityService.create(deleteLoisir);
	}
	/**
	 * Create a Sport
	 * @throws Exception
	 */
	@Test
	public void testCreateSport() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI+"sport/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
		Assert.assertTrue(jsonResponse.contains("\"type\":\"Sport\""));
	}
	
	/**
	 * Create a Sport
	 * @throws Exception
	 */
	@Test
	public void testCreateLoisir() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI+"loisir/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
		Assert.assertTrue(jsonResponse.contains("\"type\":\"Loisir\""));
	}
	/**
	 * Get a Sport
	 * @throws Exception
	 */
	@Test
	public void testGetSport() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		String expected = "{\"id\":" + getSport.getId()
				+ ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Sport\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	
	/**
	 * Get Sports
	 * @throws Exception
	 */
	@Test
	public void testGetAllSports() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/sport/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		
		Assert.assertTrue(!jsonResponse.contains("\"type\":\"Loisir\""));
		
	}
	
	/**
	 * Get Loisirs
	 * @throws Exception
	 */
	@Test
	public void testGetAllLoisirs() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/loisir/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


	
		Assert.assertTrue(!jsonResponse.contains("\"type\":\"Sport\""));
		
		
	}
	/**
	 * Update the name of a sport
	 * @throws Exception
	 */
	@Test
	public void testUpdateSport() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updateSport.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI+"sport/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateSport.getId()
				+ ",\"meteos\":[],\"name\":\"new\",\"type\":\"Sport\"}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updateSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);
	}
	
	/**
	 * Delete a Loisir
	 * @throws Exception
	 */
	@Test(expected = ActivityNotFound.class)
	public void testDeleteSport() throws Exception {

		this.mockMvc
				.perform(delete(SERVICE_URI + deleteSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + deleteSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		activityService.findById(deleteSport.getId());
	}

	
	/**
	 * Get a Loisir
	 * @throws Exception
	 */
	@Test
	public void testGetLoisir() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getLoisir.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		String expected = "{\"id\":" + getLoisir.getId()
				+ ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Loisir\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Update the name of a loisir
	 * @throws Exception
	 */
	@Test
	public void testUpdateLoisir() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updateLoisir.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI+"loisir/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateLoisir.getId()
				+ ",\"meteos\":[],\"name\":\"new\",\"type\":\"Loisir\"}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updateLoisir.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);
	}

}
