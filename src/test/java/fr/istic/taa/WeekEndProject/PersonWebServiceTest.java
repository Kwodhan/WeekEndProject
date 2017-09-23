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
import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.model.Activity.Loisir;
import fr.istic.taa.WeekEndProject.service.ActivityService;
import fr.istic.taa.WeekEndProject.service.LocationService;
import fr.istic.taa.WeekEndProject.service.PersonService;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
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

	@Autowired
	private ActivityService activityService;

	private Person getPerson;

	private Person updatePerson;

	private Person deletePerson;

	private Location createLocation;

	private Person updateLocationPerson;

	private AbstractActivity createActivity;

	private Person updateActivityPerson;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getPerson = new Person("create");
		getPerson = personService.create(getPerson);

		updatePerson = new Person("update");
		updatePerson = personService.create(updatePerson);

		createLocation = new Location("Location");
		createLocation = locationService.create(createLocation);

		updateLocationPerson = new Person("updateLocation");
		updateLocationPerson = personService.create(updateLocationPerson);

		createActivity = new Loisir("Activity");
		createActivity = activityService.create(createActivity);

		updateActivityPerson = new Person("updateActivity");
		updateActivityPerson = personService.create(updateActivityPerson);

		deletePerson = new Person("delete");
		deletePerson = personService.create(deletePerson);
	}

	/**
	 * Create a Person
	 * 
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
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPerson() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + getPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + getPerson.getId()
				+ ",\"name\":\"create\",\"homes\":[],\"activities\":[]}";
		Assert.assertEquals(expected, jsonResponse);

	}

	/**
	 * Update the name of a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePerson() throws Exception {
		String maj = "new";
		String payload = "{\"id\":\"" + updatePerson.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updatePerson.getId()
				+ ",\"name\":\"new\",\"homes\":[],\"activities\":[]}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updatePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);
	}

	/**
	 * Update the name and add a location on a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePersonWithLocation() throws Exception {

		String maj = "new";
		String payload = "{\"id\":\"" + updateLocationPerson.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc.perform(
				put(SERVICE_URI + updateLocationPerson.getId() + "/addLocation/" + createLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateLocationPerson.getId() + ",\"name\":\""
				+ updateLocationPerson.getName() + "\",\"homes\":[{\"id\":" + createLocation.getId()
				+ ",\"name\":\"" + createLocation.getName() + "\"}],\"activities\":[]}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updateLocationPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);
	}

	/**
	 * Update the name and add a Activity on a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePersonWithActivity() throws Exception {

		String maj = "new";
		String payload = "{\"id\":\"" + updateActivityPerson.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc.perform(
				put(SERVICE_URI + updateActivityPerson.getId() + "/addActivity/" + createActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateActivityPerson.getId() + ",\"name\":\""
				+ updateActivityPerson.getName() + "\",\"homes\":[],\"activities\":[{\"id\":"
				+ createActivity.getId() + ",\"meteos\":[],\"name\":\"" + createActivity.getName()
				+ "\",\"type\":\"Loisir\"}]}";
		Assert.assertEquals(expected, jsonResponse);
		
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + updateActivityPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		Assert.assertEquals(expected, jsonResponse2);

	}

	/**
	 * delete a activity and verify that the person is not delete
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteActivityPersonExist() throws Exception {

		String maj = "new";
		String payload = "{\"id\":\"" + updateActivityPerson.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc.perform(
				put(SERVICE_URI + updateActivityPerson.getId() + "/addActivity/" + createActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateActivityPerson.getId() + ",\"name\":\""
				+ updateActivityPerson.getName() + "\",\"homes\":[],\"activities\":[{\"id\":"
				+ createActivity.getId() + ",\"meteos\":[],\"name\":\"" + createActivity.getName()
				+ "\",\"type\":\"Loisir\"}]}";

		Assert.assertEquals(expected, jsonResponse);

		this.mockMvc
				.perform(delete("/activity/" + createActivity.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get("/activity/id/" + createActivity.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		try {
			activityService.findById(createActivity.getId());
			Assert.assertFalse("ActivityNotFound expected", true);
		} catch (ActivityNotFound e) {
			Assert.assertTrue("ActivityNotFound", true);
		}

	}
	/**
	 * delete a Location and verify that the person is not delete
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteLocationPersonExist() throws Exception {

		String maj = "new";
		String payload = "{\"id\":\"" + updateLocationPerson.getId() + "\",\"name\":\"" + maj + "\"}";
		String jsonResponse = this.mockMvc.perform(
				put(SERVICE_URI + updateLocationPerson.getId() + "/addLocation/" + createLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = "{\"id\":" + updateLocationPerson.getId() + ",\"name\":\""
				+ updateLocationPerson.getName() + "\",\"homes\":[{\"id\":" + createLocation.getId()
				+ ",\"name\":\"" + createLocation.getName() + "\"}],\"activities\":[]}";

		Assert.assertEquals(expected, jsonResponse);

		this.mockMvc
				.perform(delete("/location/" + createLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get("/location/id/" + createLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		try {

			locationService.findById(createLocation.getId());
			Assert.assertFalse("LocationNotFound expected", true);
		} catch (LocationNotFound e) {
			Assert.assertTrue("LocationNotFound", true);
		}

	}

	/**
	 * Delete a Person
	 * 
	 * @throws Exception
	 */
	@Test(expected = PersonNotFound.class)
	public void testDeletePerson() throws Exception {

		this.mockMvc
				.perform(delete(SERVICE_URI + "/" + deletePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + "/id/" + deletePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		personService.findById(deletePerson.getId());
	}

}
