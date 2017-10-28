package fr.istic.taa.weekEndProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.service.ActivityService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml","classpath:spring-security.xml" })
public class ActivityWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/activities/";

	@Autowired
	private ActivityService activityService;

	private Activity getSport;

	private Activity updateSport;

	private Activity deleteSport;

	private Activity createSport;

	private Activity getLeisure;

	private Activity updateLeisure;

	private Activity deleteLeisure;

	private Activity createLeisure;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		// Sport
		getSport = new Activity("Name","Sport");
		getSport = activityService.create(getSport);

		updateSport = new Activity("dsq","Sport");
		updateSport = activityService.create(updateSport);

		createSport = new Activity("qds","Sport");
		createSport = activityService.create(createSport);

		deleteSport = new Activity("cxw","Sport");
		deleteSport = activityService.create(deleteSport);

		// Leisure
		getLeisure = new Activity("Name","Leisure");
		getLeisure = activityService.create(getLeisure);

		updateLeisure = new Activity("Loisir","Leisure");
		updateLeisure = activityService.create(updateLeisure);

		createLeisure = new Activity("qds","Leisure");
		createLeisure = activityService.create(createLeisure);

		deleteLeisure = new Activity("cxw","Leisure");
		deleteLeisure = activityService.create(deleteLeisure);
	}

	/**
	 * Create a Sport
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void testCreateSport() throws Exception {
//		String payload = FactoryJSON.Activity(createSport.getId(), createSport.getName(), createSport.getType());
//		String jsonResponse = this.mockMvc
//				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		Assert.assertTrue(jsonResponse.contains("\"name\":\"" + createSport.getName() + "\""));
//		Assert.assertTrue(jsonResponse.contains("\"type\":\"Sport\""));
//	}

	/**
	 * Create a Sport
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void testCreateLeisure() throws Exception {
//
//		String payload = FactoryJSON.Activity(createLeisure.getId(), createLeisure.getName(), createLeisure.getType());
//		String jsonResponse = this.mockMvc
//				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		Assert.assertTrue(jsonResponse.contains("\"name\":\"" + createLeisure.getName() + "\""));
//		Assert.assertTrue(jsonResponse.contains("\"type\":\"Leisure\""));
//		
//	}

	/**
	 * Get a Sport
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSport() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + getSport.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + getSport.getId()
		// + ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Sport\"}";
		String expected = FactoryJSON.Activity(getSport.getId(), getSport.getName(), getSport.getType());
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse);
	}

	/**
	 * Get Sports
	 * 
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
	 * 
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
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void testUpdateSport() throws Exception {
//		String maj = "new";
//		String payload = FactoryJSON.Activity(updateSport.getId(), maj, updateSport.getType());
//		String jsonResponse = this.mockMvc
//				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		// String expected = "{\"id\":" + updateSport.getId()
//		// + ",\"meteos\":[],\"name\":\"new\",\"type\":\"Sport\"}";
//		String expected = FactoryJSON.Activity(updateSport.getId(), maj, updateSport.getType());
//		Assert.assertEquals(expected, jsonResponse);
//
//		String jsonResponse2 = this.mockMvc
//				.perform(get(SERVICE_URI + updateSport.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		expected = FactoryJSON.Get(expected);
//		Assert.assertEquals(expected, jsonResponse2);
//	}

	/**
	 * Delete a sport
	 * 
	 * @throws Exception
	 */
//	@Test(expected = ActivityNotFound.class)
//	public void testDeleteSport() throws Exception {
//		String content = FactoryJSON.Activity(deleteSport.getId(), deleteSport.getName(), deleteSport.getType());
//		this.mockMvc
//				.perform(delete(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(content))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		this.mockMvc
//				.perform(get(SERVICE_URI + deleteSport.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
//
//		activityService.findById(deleteSport.getId());
//	}

	/**
	 * Delete a Leisure with id
	 * 
	 * @throws Exception
	 */
//	@Test(expected = ActivityNotFound.class)
//	public void testDeleteLeisureId() throws Exception {
//
//		this.mockMvc
//				.perform(delete(SERVICE_URI + deleteLeisure.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		this.mockMvc
//				.perform(get(SERVICE_URI + deleteLeisure.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
//
//		activityService.findById(deleteLeisure.getId());
//	}

	/**
	 * Get a Leisure
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetLeisure() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + getLeisure.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + getLeisure.getId()
		// + ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Leisure\"}";
		String expected = FactoryJSON.Activity(getLeisure.getId(), getLeisure.getName(), getLeisure.getType());
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse);
		
	}

	/**
	 * Update the name of a Leisure
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void testUpdateLeisure() throws Exception {
//		String maj = "new";
//		String expected = FactoryJSON.Activity(updateLeisure.getId(), maj, updateLeisure.getType());
//		String jsonResponse = this.mockMvc
//				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(expected))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		Assert.assertEquals(expected, jsonResponse);
//
//		String jsonResponse2 = this.mockMvc
//				.perform(get(SERVICE_URI + updateLeisure.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		expected = FactoryJSON.Get(expected);
//		Assert.assertEquals(expected, jsonResponse2);
//	}

}
