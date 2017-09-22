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


	private AbstractActivity getSportDataset;

	private AbstractActivity updateSportDataset;

	private AbstractActivity deleteSportDataset;

	private AbstractActivity createSportDataset;
	
	private AbstractActivity getLoisirDataset;

	private AbstractActivity updateLoisirDataset;

	private AbstractActivity deleteLoisirDataset;

	private AbstractActivity createLoisirDataset;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		//Sport
		getSportDataset = new Sport("Name");
		getSportDataset = activityService.create(getSportDataset);

		updateSportDataset = new Sport("dsq");
		updateSportDataset = activityService.create(updateSportDataset);

		createSportDataset = new Sport("qds");
		createSportDataset = activityService.create(createSportDataset);

		deleteSportDataset = new Sport("cxw");
		deleteSportDataset = activityService.create(deleteSportDataset);
		
		//Loisir
		getLoisirDataset = new Loisir("Name");
		getLoisirDataset = activityService.create(getLoisirDataset);

		updateLoisirDataset = new Loisir("dsq");
		updateLoisirDataset = activityService.create(updateLoisirDataset);

		createLoisirDataset = new Loisir("qds");
		createLoisirDataset = activityService.create(createLoisirDataset);

		deleteLoisirDataset = new Loisir("cxw");
		deleteLoisirDataset = activityService.create(deleteLoisirDataset);
	}
	/**
	 * Create a AbstractActivity
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

		// F String expected = "{\"id\":" + Mockito.anyLong()+
		// ",\"name\":\"nameCreate\",\"homes\":[],\"activities\":{}}";
		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
	}
	/**
	 * Get a AbstractActivity
	 * @throws Exception
	 */
	@Test
	public void testGetSport() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getSportDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		String expected = "{\"id\":" + getSportDataset.getId()
				+ ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Sport\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Update the name of a AbstractActivity
	 * @throws Exception
	 */
	@Test
	public void testUpdateSport() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updateSportDataset.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI+"sport/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateSportDataset.getId()
				+ ",\"meteos\":[],\"name\":\"new\",\"type\":\"Sport\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	
	/**
	 * Delete a AbstractActivity
	 * @throws Exception
	 */
	@Test(expected = ActivityNotFound.class)
	public void testDeleteSport() throws Exception {

		this.mockMvc
				.perform(delete(SERVICE_URI + deleteSportDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + deleteSportDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		activityService.findById(deleteSportDataset.getId());
	}

	@Test
	public void testCreateLoisir() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI+"loisir/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// F String expected = "{\"id\":" + Mockito.anyLong()+
		// ",\"name\":\"nameCreate\",\"homes\":[],\"activities\":{}}";
		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
	}
	/**
	 * Get a AbstractActivity
	 * @throws Exception
	 */
	@Test
	public void testGetLoisir() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getLoisirDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		String expected = "{\"id\":" + getLoisirDataset.getId()
				+ ",\"meteos\":[],\"name\":\"Name\",\"type\":\"Loisir\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Update the name of a AbstractActivity
	 * @throws Exception
	 */
	@Test
	public void testUpdateLoisir() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updateLoisirDataset.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI+"loisir/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateLoisirDataset.getId()
				+ ",\"meteos\":[],\"name\":\"new\",\"type\":\"Loisir\"}";
		Assert.assertEquals(expected, jsonResponse);
	}

}
