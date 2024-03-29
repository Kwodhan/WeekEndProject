package fr.istic.taa.weekEndProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import fr.istic.taa.weekEndProject.service.LocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml","classpath:spring-security.xml" })
public class LocationWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/locations/";

	@Autowired
	private LocationService locationService;

	private Location getLocation;

	private Location updateLocation;

	private Location deleteLocation;

	private Location createLocation;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getLocation = new Location("create");
		getLocation = locationService.create(getLocation);

		updateLocation = new Location("dsq");
		updateLocation = locationService.create(updateLocation);

		createLocation = new Location("qds");
		createLocation = locationService.create(createLocation);

		deleteLocation = new Location("cxw");
		deleteLocation = locationService.create(deleteLocation);
	}

	/**
	 * Create a Location
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateLocation() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"city\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(jsonResponse.contains("\"city\":\"nameCreate\""));
	}

	/**
	 * Get a Location
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetLocation() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + getLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + getLocation.getId()
		// + ",\"city\":\"create\"}";
		String expected = FactoryJSON.Location(getLocation.getId(), getLocation.getCity(),null,null,null);
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse);
	}

	/**
	 * Update the name of a Location
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void testUpdateLocation() throws Exception {
//		// Assert.assertEquals(updateLocation.getName(),updateLocation.getId());
//		String maj = "azerty";
//		String payload = "{\"id\":" + updateLocation.getId() + ",\"city\":\"" + maj + "\"}";
//		String jsonResponse = this.mockMvc
//				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		// String expected = "{\"id\":" + updateLocation.getId()
//		// + ",\"city\":\"azerty\"}";
//		String expected = FactoryJSON.Location(updateLocation.getId(), maj,null,null,null);
//		Assert.assertEquals(expected, jsonResponse);
//
//		String jsonResponse2 = this.mockMvc
//				.perform(get(SERVICE_URI + updateLocation.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		expected = FactoryJSON.Get(expected);
//		Assert.assertEquals(expected, jsonResponse2);
//	}

	/**
	 * Delete a Location with json
	 * 
	 * @throws Exception
	 */
//	@Test(expected = LocationNotFound.class)
//	public void testDeleteLocation() throws Exception {
//		String content = FactoryJSON.Location(deleteLocation.getId(), deleteLocation.getCity(),null,null,null);
//		this.mockMvc
//				.perform(delete(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(content))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		this.mockMvc
//				.perform(get(SERVICE_URI + deleteLocation.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
//
//		locationService.findById(deleteLocation.getId());
//	}
	
	/**
	 * Delete a Location with id
	 * 
	 * @throws Exception
	 */
//	@Test(expected = LocationNotFound.class)
//	public void testDeleteLocationId() throws Exception {
//		
//		this.mockMvc
//				.perform(delete(SERVICE_URI+deleteLocation.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		this.mockMvc
//				.perform(get(SERVICE_URI + deleteLocation.getId()).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
//
//		locationService.findById(deleteLocation.getId());
//	}

}
