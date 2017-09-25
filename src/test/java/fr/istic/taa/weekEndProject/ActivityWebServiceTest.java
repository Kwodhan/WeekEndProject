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

import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.model.activity.Leisure;
import fr.istic.taa.weekEndProject.model.activity.Sport;
import fr.istic.taa.weekEndProject.service.ActivityService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class ActivityWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/activities/";

	@Autowired
	private ActivityService activityService;


	private AbstractActivity getSport;

	private AbstractActivity updateSport;

	private AbstractActivity deleteSport;

	private AbstractActivity createSport;
	
	private AbstractActivity getLeisure;

	private AbstractActivity updateLeisure;

	private AbstractActivity deleteLeisure;

	private AbstractActivity createLeisure;

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
		
		//Leisure
		getLeisure = new Leisure("Name");
		getLeisure = activityService.create(getLeisure);

		updateLeisure = new Leisure("Loisir");
		updateLeisure = activityService.create(updateLeisure);

		createLeisure = new Leisure("qds");
		createLeisure = activityService.create(createLeisure);

		deleteLeisure = new Leisure("cxw");
		deleteLeisure = activityService.create(deleteLeisure);
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
				.perform(post(SERVICE_URI+"sports/").contentType(MediaType.APPLICATION_JSON)
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
	public void testCreateLeisure() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI+"/leisures/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
		Assert.assertTrue(jsonResponse.contains("\"type\":\"Leisure\""));
	}
	/**
	 * Get a Sport
	 * @throws Exception
	 */
	@Test
	public void testGetSport() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI +  getSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


//		String expected = "{\"id\":" + getSport.getId()
//				+ ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Sport\"}";
		String expected = FactoryJSON.Activity(getSport.getId(), getSport.getName(), getSport.getType());
		Assert.assertEquals(expected, jsonResponse);
	}
	
	/**
	 * Get Sports
	 * @throws Exception
	 */
	@Test
	public void testGetAllSports() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/sports/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		
		Assert.assertTrue(!jsonResponse.contains("\"type\":\"Leisure\""));
		
	}
	
	/**
	 * Get Leisures
	 * @throws Exception
	 */
	@Test
	public void testGetAllLeisures() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/leisures/").contentType(MediaType.APPLICATION_JSON)
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
				.perform(put(SERVICE_URI+"sports/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

//		String expected = "{\"id\":" + updateSport.getId()
//				+ ",\"meteos\":[],\"name\":\"new\",\"type\":\"Sport\"}";
		String expected = FactoryJSON.Activity(updateSport.getId(), maj, updateSport.getType());
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI +  updateSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);
	}
	
	/**
	 * Delete a Leisure
	 * @throws Exception
	 */
	@Test(expected = ActivityNotFound.class)
	public void testDeleteSport() throws Exception {

		this.mockMvc
				.perform(delete(SERVICE_URI + deleteSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + deleteSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		activityService.findById(deleteSport.getId());
	}

	
	/**
	 * Get a Leisure
	 * @throws Exception
	 */
	@Test
	public void testGetLeisure() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI  + getLeisure.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


//		String expected = "{\"id\":" + getLeisure.getId()
//				+ ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Leisure\"}";
		String expected = FactoryJSON.Activity(getLeisure.getId(), getLeisure.getName(), getLeisure.getType());
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Update the name of a Leisure
	 * @throws Exception
	 */
	@Test
	public void testUpdateLeisure() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updateLeisure.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI+"leisures/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

//		String expected = "{\"id\":" + updateLeisure.getId()
//				+ ",\"meteos\":[],\"name\":\"new\",\"type\":\"Leisure\"}";
		String expected = FactoryJSON.Activity(updateLeisure.getId(), maj, updateLeisure.getType());
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI +  updateLeisure.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);
	}

}
