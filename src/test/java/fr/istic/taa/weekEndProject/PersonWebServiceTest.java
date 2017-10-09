package fr.istic.taa.weekEndProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

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
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.ActivityService;
import fr.istic.taa.weekEndProject.service.LocationService;
import fr.istic.taa.weekEndProject.service.UserService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:TestApplicationContext.xml","classpath:spring-security.xml" })
public class PersonWebServiceTest {

	private MockMvc mockMvc;

	private static final String SERVICE_URI = "/users/";

	@Autowired
	private UserService personService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private ActivityService activityService;

	private User getPerson;

	private User updatePerson;

	private User deletePerson;

	private Location createLocation;

	private Location updateLocation;

	private Location updateLocation2;

	private User updateLocationPerson;

	private Activity createActivity;

	private Activity updateActivity;

	private Activity updateActivity2;

	private User updateActivityPerson;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		getPerson = new User("pseudo","hello","create", "last");
		getPerson = personService.createUser(getPerson);

		updatePerson = new User("pseudo","hello","update", "last");
		updatePerson = personService.createUser(updatePerson);

		createLocation = new Location("Location");
		createLocation = locationService.create(createLocation);

		updateLocation = new Location("location1");
		updateLocation = locationService.create(updateLocation);

		updateLocation2 = new Location("lFocation2");
		updateLocation2 = locationService.create(updateLocation2);

		updateLocationPerson = new User("pseudo","hello","updateLocation", "last");
		updateLocationPerson = personService.createUser(updateLocationPerson);

		createActivity = new Activity("Activity","Leisure");
		createActivity = activityService.create(createActivity);

		updateActivity = new Activity("sport","Sport");
		updateActivity = activityService.create(updateActivity);

		updateActivity2 = new Activity("leisure","Leisure");
		updateActivity2 = activityService.create(updateActivity2);

		updateActivityPerson = new User("pseudo","hello","updateActivity", "last");
		updateActivityPerson = personService.createUser(updateActivityPerson);

		deletePerson = new User("pseudo","hello","delete", "last");
		deletePerson = personService.createUser(deletePerson);
	}

	/**
	 * Create a Person
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void testCreatePerson() throws Exception {
//
//		String payload = FactoryJSON.Person(new Long(1234),"pseudo","azerty" ,"firstname", "lastname", "email");
//		String jsonResponse = this.mockMvc
//				.perform(post(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		// F String expected = "{\"id\":" + Mockito.anyLong()+
//		// ",\"name\":\"nameCreate\",\"homes\":[],\"activities\":{}}";
//		Assert.assertTrue(jsonResponse.contains(
//				"\"firstName\":\"firstname\",\"lastName\":\"lastname\",\"emailAddress\":\"email\",\"homes\":[],\"activities\":[]"));
//		// Assert.assertEquals(payload, jsonResponse);
//	}

	/**
	 * Get a person by id
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
		String expected = FactoryJSON.Person(getPerson.getId(),getPerson.getPseudo(),getPerson.getPassword(), getPerson.getFirstName(), getPerson.getLastName(),
				getPerson.getEmailAddress(),"ROLE_USER");
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse);

	}

	/**
	 * Update the firstname, lastname, and the email of a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePerson() throws Exception {
		String maj1 = "new1";
		String maj2 = "new2";
		String maj3 = "new3";
		String payload = FactoryJSON.Person(updatePerson.getId(),null,null, maj1, maj2, maj3,"ROLE_USER");
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = FactoryJSON.Person(updatePerson.getId(), maj1, maj2, maj3);
		String expected = FactoryJSON.Person(updatePerson.getId(),updatePerson.getPseudo(),updatePerson.getPassword(), maj1, maj2, maj3,"ROLE_USER");
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updatePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse2);
	}

	/**
	 * Update all the homes on a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePersonWithLocation() throws Exception {
		HashSet<Location> locations = new HashSet<Location>();
		locations.add(createLocation);

		String jsonResponse = this.mockMvc
				.perform(post(SERVICE_URI + updateLocationPerson.getId() + "/homes/")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(FactoryJSON.ArrayLocation(locations)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = FactoryJSON.PersonLocation(updateLocationPerson.getId(),updateLocationPerson.getPseudo(),updateLocationPerson.getPassword(), updateLocationPerson.getFirstName(),
				updateLocationPerson.getLastName(), updateLocationPerson.getEmailAddress(), locations,"ROLE_USER");
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updateLocationPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse2);
	}

	/**
	 * Update all the Activities on a Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePersonWithActivity() throws Exception {
		HashSet<Activity> activities = new HashSet<Activity>();
		activities.add(createActivity);
		String content = FactoryJSON.ArrayActivity(activities);

		String jsonResponse = this.mockMvc.perform(post(SERVICE_URI + updateActivityPerson.getId() + "/activities/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = FactoryJSON.PersonActivity(updateActivityPerson.getId(),updateActivityPerson.getPseudo(),updateActivityPerson.getPassword(), updateActivityPerson.getFirstName(),
				updateActivityPerson.getLastName(), updateActivityPerson.getEmailAddress(), activities,"ROLE_USER");
		Assert.assertEquals(expected, jsonResponse);

		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updateActivityPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		expected = FactoryJSON.Get(expected);
		Assert.assertEquals(expected, jsonResponse2);

	}

	/**
	 * delete a activity and verify that the person is not delete and the activity
	 * is not anymore in the Person
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteActivityPersonExist() throws Exception {
		HashSet<Activity> activities = new HashSet<Activity>();
		activities.add(createActivity);
		String payload = FactoryJSON.ArrayActivity(activities);
		String jsonResponse = this.mockMvc.perform(post(SERVICE_URI + updateActivityPerson.getId() + "/activities/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String expected = FactoryJSON.PersonActivity(updateActivityPerson.getId(),updateActivityPerson.getPseudo(),updateActivityPerson.getPassword(), updateActivityPerson.getFirstName(),
				updateActivityPerson.getLastName(), updateActivityPerson.getEmailAddress(), activities,"ROLE_USER");
		Assert.assertEquals(expected, jsonResponse);
		String content = FactoryJSON.Activity(createActivity.getId(), createActivity.getName(),
				createActivity.getType());
		this.mockMvc
				.perform(delete("/activities/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(content))
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

		String expected2 = FactoryJSON.Person(updateActivityPerson.getId(),updateActivityPerson.getPseudo(),updateActivityPerson.getPassword(), updateActivityPerson.getFirstName(),
				updateActivityPerson.getLastName(), updateActivityPerson.getEmailAddress(),"ROLE_USER");
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updateActivityPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		expected2 = FactoryJSON.Get(expected2);
		Assert.assertEquals(expected2, jsonResponse2);

	}

	/**
	 * delete a Location and verify that the person is not delete and the location
	 * is not anymore in the Person
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteLocationPersonExist() throws Exception {
		HashSet<Location> locations = new HashSet<Location>();
		locations.add(createLocation);
		String payload = FactoryJSON.ArrayLocation(locations);
		String jsonResponse = this.mockMvc.perform(post(SERVICE_URI + updateLocationPerson.getId() + "/homes/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(payload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// String expected = "{\"id\":" + updateLocationPerson.getId() +
		// ",\"firstName\":\""
		// + updateLocationPerson.getFirstName() + "\",\"homes\":[{\"id\":" +
		// createLocation.getId()
		// + ",\"city\":\"" + createLocation.getCity() + "\"}],\"activities\":[]}";

		String expected = FactoryJSON.PersonLocation(updateLocationPerson.getId(),updateLocationPerson.getPseudo(),updateLocationPerson.getPassword(), updateLocationPerson.getFirstName(),
				updateLocationPerson.getLastName(), updateLocationPerson.getEmailAddress(), locations,"ROLE_USER");
		Assert.assertEquals(expected, jsonResponse);

		String content = FactoryJSON.Location(createLocation.getId(), createLocation.getCity(),null,null,null);
		this.mockMvc
				.perform(delete("/locations/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(content))
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

		String expected2 = FactoryJSON.Person(updateLocationPerson.getId(),updateLocationPerson.getPseudo(),updateLocationPerson.getPassword(), updateLocationPerson.getFirstName(),
				updateLocationPerson.getLastName(), updateLocationPerson.getEmailAddress(),"ROLE_USER");
		String jsonResponse2 = this.mockMvc
				.perform(get(SERVICE_URI + updateLocationPerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		expected2 = FactoryJSON.Get(expected2);
		Assert.assertEquals(expected2, jsonResponse2);

	}

	/**
	 * Delete a Person Note : just need the id (like the others entity)
	 * 
	 * @throws Exception
	 */
	@Test(expected = PersonNotFound.class)
	public void testDeletePerson() throws Exception {
		String content = FactoryJSON.Person(deletePerson.getId(),null,null, "we don't", "care", null,"ROLE_USER");
		this.mockMvc
				.perform(delete(SERVICE_URI).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		this.mockMvc
				.perform(get(SERVICE_URI + deletePerson.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

		personService.findById(deletePerson.getId());
	}

	/**
	 * Delete a Person with id Note
	 * 
	 * @throws Exception
	 */
	@Test(expected = PersonNotFound.class)
	public void testDeletePersonId() throws Exception {

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

	/**
	 * Add two activities
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddActivities() throws Exception {
		Set<Activity> set = new HashSet<Activity>();
		set.add(updateActivity);
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/activities/" + updateActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = FactoryJSON.PersonActivity(updatePerson.getId(),updatePerson.getPseudo(),updatePerson.getPassword(), updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse, expected);

		set.add(updateActivity2);
		String jsonResponse2 = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/activities/" + updateActivity2.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertTrue(jsonResponse2.contains(
				FactoryJSON.Activity(updateActivity.getId(), updateActivity.getName(), updateActivity.getType())));
		Assert.assertTrue(jsonResponse2.contains(
				FactoryJSON.Activity(updateActivity2.getId(), updateActivity2.getName(), updateActivity2.getType())));
	}

	/**
	 * Add two activities then delete one
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteActivities() throws Exception {
		Set<Activity> set = new HashSet<Activity>();
		set.add(updateActivity);
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/activities/" + updateActivity.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = FactoryJSON.PersonActivity(updatePerson.getId(),updatePerson.getPseudo(),updatePerson.getPassword(), updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse, expected);

		set.add(updateActivity2);
		String jsonResponse2 = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/activities/" + updateActivity2.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(jsonResponse2.contains(
				FactoryJSON.Activity(updateActivity.getId(), updateActivity.getName(), updateActivity.getType())));
		Assert.assertTrue(jsonResponse2.contains(
				FactoryJSON.Activity(updateActivity2.getId(), updateActivity2.getName(), updateActivity2.getType())));

		String jsonResponse3 = this.mockMvc
				.perform(delete(SERVICE_URI + updatePerson.getId() + "/activities/" + updateActivity2.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		set.remove(updateActivity2);
		String expected3 = FactoryJSON.PersonActivity(updatePerson.getId(), updatePerson.getPseudo(),updatePerson.getPassword(),updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse3, expected3);
	}

	/**
	 * Add two locations
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddLocations() throws Exception {
		Set<Location> set = new HashSet<Location>();
		set.add(updateLocation);
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/homes/" + updateLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = FactoryJSON.PersonLocation(updatePerson.getId(),updatePerson.getPseudo(),null, updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse, expected);

		set.add(updateLocation2);
		String jsonResponse2 = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/homes/" + updateLocation2.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertTrue(
				jsonResponse2.contains(FactoryJSON.Location(updateLocation.getId(), updateLocation.getCity(),null,null,null)));
		Assert.assertTrue(
				jsonResponse2.contains(FactoryJSON.Location(updateLocation2.getId(), updateLocation2.getCity(),null,null,null)));
	}

	/**
	 * Add two locations and delete them one by one
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteLocations() throws Exception {
		Set<Location> set = new HashSet<Location>();
		set.add(updateLocation);
		String jsonResponse = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/homes/" + updateLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = FactoryJSON.PersonLocation(updatePerson.getId(),updatePerson.getPseudo(),updatePerson.getPassword(), updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse, expected);

		set.add(updateLocation2);
		String jsonResponse2 = this.mockMvc
				.perform(put(SERVICE_URI + updatePerson.getId() + "/homes/" + updateLocation2.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertTrue(
				jsonResponse2.contains(FactoryJSON.Location(updateLocation.getId(), updateLocation.getCity(),null,null,null)));
		Assert.assertTrue(
				jsonResponse2.contains(FactoryJSON.Location(updateLocation2.getId(), updateLocation2.getCity(),null,null,null)));

		String jsonResponse3 = this.mockMvc
				.perform(delete(SERVICE_URI + updatePerson.getId() + "/homes/" + updateLocation2.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		set.remove(updateLocation2);
		String expected3 = FactoryJSON.PersonLocation(updatePerson.getId(),updatePerson.getPseudo(),updatePerson.getPassword(), updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse3, expected3);

		String jsonResponse4 = this.mockMvc
				.perform(delete(SERVICE_URI + updatePerson.getId() + "/homes/" + updateLocation.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		set.remove(updateLocation);
		String expected4 = FactoryJSON.PersonLocation(updatePerson.getId(),updatePerson.getPseudo(),updatePerson.getPassword(), updatePerson.getFirstName(),
				updatePerson.getLastName(), updatePerson.getEmailAddress(), set,"ROLE_USER");
		Assert.assertEquals(jsonResponse4, expected4);
	}

}
