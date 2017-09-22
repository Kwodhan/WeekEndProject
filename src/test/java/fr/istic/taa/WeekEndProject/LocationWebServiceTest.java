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

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.service.LocationService;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class LocationWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/location/";

	@Autowired
	private LocationService locationService;


	private Location getLocationDataset;

	private Location updateLocationDataset;

	private Location deleteLocationDataset;

	private Location createLocationDataset;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getLocationDataset = new Location("create");
		getLocationDataset = locationService.create(getLocationDataset);

		updateLocationDataset = new Location("dsq");
		updateLocationDataset = locationService.create(updateLocationDataset);

		createLocationDataset = new Location("qds");
		createLocationDataset = locationService.create(createLocationDataset);

		deleteLocationDataset = new Location("cxw");
		deleteLocationDataset = locationService.create(deleteLocationDataset);
	}
	/**
	 * Create a Location
	 * @throws Exception
	 */
	@Test
	public void testCreateLocation() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
	}
	/**
	 * Get a Location
	 * @throws Exception
	 */
	@Test
	public void testGetLocation() throws Exception {
		
		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getLocationDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + getLocationDataset.getId()
				+ ",\"name\":\"create\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Update the name of a Location
	 * @throws Exception
	 */
	@Test
	public void testUpdateLocation() throws Exception {
		//Assert.assertEquals(updateLocationDataset.getName(),updateLocationDataset.getId());
		String maj = "azerty";
		String payload = "{\"id\":" + updateLocationDataset.getId() + ",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateLocationDataset.getId()
				+ ",\"name\":\"azerty\"}";
		Assert.assertEquals(expected, jsonResponse);
	}
	
	/**
	 * Delete a Location
	 * @throws Exception
	 */
	@Test(expected = LocationNotFound.class)
	public void testDeleteLocation() throws Exception {

		this.mockMvc
				.perform(delete(SERVICE_URI + "/" + deleteLocationDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + deleteLocationDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		locationService.findById(deleteLocationDataset.getId());
	}

	

}
