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
import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.service.LocationService;
import fr.istic.taa.WeekEndProject.service.PersonService;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class PersonWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/person/";

	@Autowired
	private PersonService personService;

	@Autowired
	private LocationService locationService;

	private Person getPersonDataset;

	private Person updatePersonDataset;

	private Person updateLocationPersonDataset;

	private Person deletePersonDataset;

	private Location createLocationDataset;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getPersonDataset = new Person("create");
		getPersonDataset = personService.create(getPersonDataset);

		updatePersonDataset = new Person("update");
		updatePersonDataset = personService.create(updatePersonDataset);

		createLocationDataset = new Location("Location");
		createLocationDataset = locationService.create(createLocationDataset);

		updateLocationPersonDataset = new Person("updateLocation");
		updateLocationPersonDataset = personService.create(updateLocationPersonDataset);

		deletePersonDataset = new Person("delete");
		deletePersonDataset = personService.create(deletePersonDataset);
	}
	/**
	 * Create a Person
	 * @throws Exception
	 */
	@Test
	public void testCreatePerson() throws Exception {
		String maj = "nameCreate";
		String payload = "{\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// F String expected = "{\"id\":" + Mockito.anyLong()+
		// ",\"name\":\"nameCreate\",\"homes\":[],\"activities\":{}}";
		Assert.assertTrue(jsonResponse.contains("\"name\":\"nameCreate\""));
	}
	/**
	 * Get a person
	 * @throws Exception
	 */
	@Test
	public void testGetPerson() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getPersonDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + getPersonDataset.getId()
				+ ",\"name\":\"create\",\"homes\":[],\"activities\":{}}";
		Assert.assertEquals(expected, jsonResponse);
		
	}
	/**
	 * Update the name of a Person
	 * @throws Exception
	 */
	@Test
	public void testUpdatePerson() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updatePersonDataset.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updatePersonDataset.getId()
				+ ",\"name\":\"new\",\"homes\":[],\"activities\":{}}";
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Update the name and add a location on a Person
	 * @throws Exception
	 */
	@Test
	public void testUpdatePersonWithLocation() throws Exception {

		String maj = "new";
		String payload = "{\"id\":\"" + updateLocationPersonDataset.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc.perform(
				put(SERVICE_URI + updateLocationPersonDataset.getId() + "/addLocation/" + createLocationDataset.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateLocationPersonDataset.getId() + ",\"name\":\""
				+ updateLocationPersonDataset.getName() + "\",\"homes\":[{\"id\":" + createLocationDataset.getId()
				+ ",\"name\":\"" + createLocationDataset.getName() + "\"}],\"activities\":{}}";
		Assert.assertEquals(expected, jsonResponse);
	}
	/**
	 * Delete a Person
	 * @throws Exception
	 */
	@Test(expected = PersonNotFound.class)
	public void testDeletePerson() throws Exception {

		this.mockMvc
				.perform(delete(SERVICE_URI + "/" + deletePersonDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + deletePersonDataset.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		personService.findById(deletePersonDataset.getId());
	}

	

}
