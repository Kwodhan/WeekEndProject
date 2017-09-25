package fr.istic.taa.weekEndProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;

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
import fr.istic.taa.weekEndProject.model.Person;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.model.activity.Leisure;
import fr.istic.taa.weekEndProject.service.ActivityService;
import fr.istic.taa.weekEndProject.service.LocationService;
import fr.istic.taa.weekEndProject.service.PersonService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml" })
public class PersonWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/persons/";

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
		getPerson = new Person("create", "last");
		getPerson = personService.create(getPerson);

		updatePerson = new Person("update", "last");
		updatePerson = personService.create(updatePerson);

		createLocation = new Location("Location");
		createLocation = locationService.create(createLocation);

		updateLocationPerson = new Person("updateLocation", "last");
		updateLocationPerson = personService.create(updateLocationPerson);

		createActivity = new Leisure("Activity");
		createActivity = activityService.create(createActivity);

		updateActivityPerson = new Person("updateActivity", "last");
		updateActivityPerson = personService.create(updateActivityPerson);

		deletePerson = new Person("delete", "last");
		deletePerson = personService.create(deletePerson);
	}

	/**
	 * Create a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreatePerson() throws Exception {

		String payload = FactoryJSON.Person(new Long(1234), "firstname", "lastname",
				"email");
		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// F String expected = "{\"id\":" + Mockito.anyLong()+
		// ",\"name\":\"nameCreate\",\"homes\":[],\"activities\":{}}";
		Assert.assertTrue(jsonResponse.contains("\"firstName\":\"firstname\",\"lastName\":\"lastname\",\"emailAddress\":\"email\",\"homes\":[],\"activities\":[]"));
		//Assert.assertEquals(payload, jsonResponse);
	}

	/**
	 * Get a person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPerson() throws Exception {

		String jsonResponse = this.mockMvc
				.perform(get(SERVICE_URI + getPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + getPerson.getId()
		// + ",\"name\":\"create\",\"homes\":[],\"activities\":[]}";
		String expected = FactoryJSON.Person(getPerson.getId(), getPerson.getFirstName(), getPerson.getLastName(),
				getPerson.getEmailAddress());
		Assert.assertEquals(expected, jsonResponse);

	}

	/**
	 * Update the name of a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePerson() throws Exception {
		String maj1 = "new1";
		String maj2 = "new2";
		String maj3 = "new3";
		String payload = FactoryJSON.Person(updatePerson.getId(), maj1, maj2, maj3);
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = FactoryJSON.Person(updatePerson.getId(), maj1, maj2, maj3);
		String expected = FactoryJSON.Person(updatePerson.getId(), maj1, maj2, maj3);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updatePerson.getId()).contentType(MediaType.APPLICATION_JSON)
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

	
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updateLocationPerson.getId() + "/addLocation/" + createLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						)
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + updateLocationPerson.getId() +
		// ",\"firstName\":\""
		// + updateLocationPerson.getFirstName() + "\",\"homes\":[{\"id\":" +
		// createLocation.getId()
		// + ",\"city\":\"" + createLocation.getCity() + "\"}],\"activities\":[]}";
		HashSet<Location> locations = new HashSet<Location>();
		locations.add(createLocation);
		String expected = FactoryJSON.PersonLocation(updateLocationPerson.getId(), updateLocationPerson.getFirstName(), updateLocationPerson.getLastName(), updateLocationPerson.getEmailAddress(), locations);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updateLocationPerson.getId()).contentType(MediaType.APPLICATION_JSON)
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

	
	
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updateActivityPerson.getId() + "/addActivity/" + createActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						)
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + updateActivityPerson.getId() +
		// ",\"firstName\":\""
		// + updateActivityPerson.getFirstName() +
		// "\",\"homes\":[],\"activities\":[{\"id\":"
		// + createActivity.getId() + ",\"meteos\":[],\"name\":\"" +
		// createActivity.getName()
		// + "\",\"type\":\"Leisure\"}]}";
		HashSet<AbstractActivity> activities = new HashSet<AbstractActivity>();
		activities.add(createActivity);
		String expected = FactoryJSON.PersonActivity(updateActivityPerson.getId(), updateActivityPerson.getFirstName(), updateActivityPerson.getLastName(), updateActivityPerson.getEmailAddress(), activities);
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updateActivityPerson.getId()).contentType(MediaType.APPLICATION_JSON)
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

		
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updateActivityPerson.getId() + "/addActivity/" + createActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						)
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

//		String expected = "{\"id\":" + updateActivityPerson.getId() + ",\"firstName\":\""
//				+ updateActivityPerson.getFirstName() + "\",\"homes\":[],\"activities\":[{\"id\":"
//				+ createActivity.getId() + ",\"meteos\":[],\"name\":\"" + createActivity.getName()
//				+ "\",\"type\":\"Leisure\"}]}";
		
		HashSet<AbstractActivity> activities = new HashSet<AbstractActivity>();
		activities.add(createActivity);
		String expected = FactoryJSON.PersonActivity(updateActivityPerson.getId(), updateActivityPerson.getFirstName(), updateActivityPerson.getLastName(), updateActivityPerson.getEmailAddress(), activities);
		Assert.assertEquals(expected, jsonResponse);


		this.mockMvc
				.perform(delete("/activities/" + createActivity.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get("/activities/" + createActivity.getId()).contentType(MediaType.APPLICATION_JSON)
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
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updateLocationPerson.getId() + "/addLocation/" + createLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

//		String expected = "{\"id\":" + updateLocationPerson.getId() + ",\"firstName\":\""
//				+ updateLocationPerson.getFirstName() + "\",\"homes\":[{\"id\":" + createLocation.getId()
//				+ ",\"city\":\"" + createLocation.getCity() + "\"}],\"activities\":[]}";

		HashSet<Location> locations = new HashSet<Location>();
		locations.add(createLocation);
		String expected = FactoryJSON.PersonLocation(updateLocationPerson.getId(), updateLocationPerson.getFirstName(), updateLocationPerson.getLastName(), updateLocationPerson.getEmailAddress(), locations);
		Assert.assertEquals(expected, jsonResponse);

		this.mockMvc
				.perform(delete("/locations/" + createLocation.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get("/locations/" + createLocation.getId()).contentType(MediaType.APPLICATION_JSON)
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
				.perform(delete(SERVICE_URI + deletePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + deletePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		personService.findById(deletePerson.getId());
	}

}
