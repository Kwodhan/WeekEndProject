package fr.istic.taa.weekEndProject;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;

import fr.istic.taa.weekEndProject.jpa.JpaTest;
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.model.activity.Sport;

public class FactoryJSON {
	// ===========Person===============//
	public static String Person(Long id, String firstName, String lastName, String email) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);

		String expected = "{\"id\":" + id + ",\"firstName\":" + firstName + "" + ",\"lastName\":" + lastName + ""
				+ ",\"emailAddress\":" + email + "" + ",\"homes\":[],\"activities\":[]}";
		return expected;
	}

	public static String Person(Long id, String firstName, String lastName, String email, Set<Location> locations,
			Set<AbstractActivity> activities) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		String strlocations = ArrayLocation(locations);
		String stractivities = ArrayActivity(activities);
		
		String expected = "{\"id\":" + id + ",\"firstName\":" + firstName + "" + ",\"lastName\":" + lastName + ""
				+ ",\"emailAddress\":" + email + "" + ",\"homes\":" + strlocations + ",\"activities\":" + stractivities
				+ "}";
		return expected;
	}

	public static String PersonActivity(Long id, String firstName, String lastName, String email,
			Set<AbstractActivity> activities) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		String stractivities = ArrayActivity(activities);
		String expected = "{\"id\":" + id + ",\"firstName\":" + firstName + "" + ",\"lastName\":" + lastName + ""
				+ ",\"emailAddress\":" + email + "" + ",\"homes\":[],\"activities\":" + stractivities + "}";
		return expected;
	}

	public static String PersonLocation(Long id, String firstName, String lastName, String email,
			Set<Location> locations) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		String strlocations = "";
		for (Location l : locations) {
			strlocations = strlocations + Location(l.getId(), l.getCity());
		}
		String expected = "{\"id\":" + id + ",\"firstName\":" + firstName + "" + ",\"lastName\":" + lastName + ""
				+ ",\"emailAddress\":" + email + "" + ",\"homes\":[" + strlocations + "],\"activities\":[]}";
		return expected;
	}

	// ===========EndPerson===============//
	// ===========Location===============//
	public static String Location(Long id, String city) {
		city = addG(city);

		String expected = "{\"id\":" + id + ",\"city\":" + city + "}";
		return expected;
	}

	public static String ArrayLocation(Set<Location> locations) {
		String expected = "[";
		for (Location l : locations) {
			expected = expected + Location(l.getId(), l.getCity()) + ",";
		}
		expected = expected + "]";
		expected = expected.replaceAll(",]", "]");
		return expected;
	}
	// ===========EndLocation===============//

	// ===========Activity===============//
	public static String Activity(Long id, String name, String type) {
		name = addG(name);
		type = addG(type);

		String expected = "{\"type\":" + type + ",\"id\":" + id + ",\"name\":" + name + ",\"meteos\":[]}";
		return expected;
	}

	public static String ArrayActivity(Set<AbstractActivity> activities) {
		String expected = "[";
		for (AbstractActivity a : activities) {
			expected = expected + Activity(a.getId(), a.getName(), a.getType()) + ",";
		}
		expected = expected + "]";
		expected = expected.replaceAll(",]", "]");
		return expected;
	}
	// ===========EndActivity===============//

	// ===========Site===============//
	public static String Site(Long id, String name) {
		name = addG(name);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":null,\"activities\":[]}";
		return expected;
	}

	public static String Site(Long id, String name, Location location, Set<AbstractActivity> activities) {
		name = addG(name);

		String strlocation = Location(location.getId(), location.getCity());
		String stractivities = ArrayActivity(activities);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":" + strlocation + ",\"activities\":"
				+ stractivities + "}";
		return expected;
	}

	public static String Site(Long id, String name, Location location) {
		name = addG(name);

		String strlocation = Location(location.getId(), location.getCity());

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":" + strlocation
				+ ",\"activities\":[]}";
		return expected;
	}

	public static String Site(Long id, String name, Set<AbstractActivity> activities) {
		name = addG(name);

		String stractivities = ArrayActivity(activities);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":null,\"activities\":" + stractivities
				+ "}";
		return expected;
	}

	// ===========EndSite===============//
	private static String addG(String arg) {
		if (arg == null) {
			return "null";
		} else if (arg.equals("")) {
			return "null";
		}

		return "\"" + arg + "\"";
	}

	
	
//	public static void main(String[] args) {
//		Set<AbstractActivity> set = new HashSet<AbstractActivity>();
//		set.add(new Sport("Merde"));
//		String strActivity = FactoryJSON.ArrayActivity(set);
//		System.out.println(strActivity);
//
//	}
}
