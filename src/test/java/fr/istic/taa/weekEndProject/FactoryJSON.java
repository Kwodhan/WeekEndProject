package fr.istic.taa.weekEndProject;

import java.util.Set;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.Role;

public class FactoryJSON {
	// ===========Person===============//
	public static String Person(Long id, String pseudo, String password, String firstName, String lastName,
			String email, String role) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		pseudo = addG(pseudo);
		password = addG(password);
		role = addG(role);
		String expected = "{\"id\":" + id + ",\"pseudo\":" + pseudo + ",\"firstName\":" + firstName + ""
				+ ",\"lastName\":" + lastName + "" + ",\"emailAddress\":" + email + ""
				+ ",\"homes\":[],\"activities\":[]" + ",\"roles\":[" + role + "]}";
		return expected;
	}

	public static String Person(Long id, String pseudo, String password, String firstName, String lastName,
			String email, Set<Location> locations, Set<Activity> activities, String role) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		pseudo = addG(pseudo);
		password = addG(password);
		role = addG(role);
		String strlocations = ArrayLocation(locations);
		String stractivities = ArrayActivity(activities);

		String expected = "{\"id\":" + id + ",\"pseudo\":" + pseudo + ",\"firstName\":" + firstName + ""
				+ ",\"lastName\":" + lastName + "" + ",\"emailAddress\":" + email + "" + ",\"homes\":" + strlocations
				+ ",\"activities\":" + stractivities + ",\"roles\":[" + role + "]}";
		return expected;
	}

	public static String PersonActivity(Long id, String pseudo, String password, String firstName, String lastName,
			String email, Set<Activity> activities, String role) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		pseudo = addG(pseudo);
		password = addG(password);
		role = addG(role);
		String stractivities = ArrayActivity(activities);
		String expected = "{\"id\":" + id + ",\"pseudo\":" + pseudo + ",\"firstName\":" + firstName + ""
				+ ",\"lastName\":" + lastName + "" + ",\"emailAddress\":" + email + "" + ",\"homes\":[],\"activities\":"
				+ stractivities + ",\"roles\":[" + role + "]}";
		return expected;
	}

	public static String PersonLocation(Long id, String pseudo, String password, String firstName, String lastName,
			String email, Set<Location> locations, String role) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		pseudo = addG(pseudo);
		password = addG(password);
		role = addG(role);
		String strlocations = "";
		for (Location l : locations) {
			strlocations = strlocations + Location(l.getId(), l.getCity(),null,null,null);
		}
		String expected = "{\"id\":" + id + ",\"pseudo\":" + pseudo + ",\"firstName\":" + firstName + ""
				+ ",\"lastName\":" + lastName + "" + ",\"emailAddress\":" + email + "" + ",\"homes\":[" + strlocations
				+ "],\"activities\":[]" + ",\"roles\":[" + role + "]}";
		return expected;
	}

	// ===========EndPerson===============//
	// ===========Location===============//
	public static String Location(Long id, String city,String region, String latitude,String longitude) {
		city = addG(city);
		region = addG(region);
		latitude = addG(latitude);
		longitude = addG(longitude);
		
		String expected = "{\"id\":" + id + ",\"city\":" + city +",\"region\":" + region+",\"latitude\":" + latitude+",\"longitude\":" + longitude+ "}";
		return expected;
	}

	public static String ArrayLocation(Set<Location> locations) {
		String expected = "[";
		for (Location l : locations) {
			expected = expected + Location(l.getId(), l.getCity(),null,null,null) + ",";
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

		String expected = "{\"type\":" + type +",\"id\":" + id + ",\"name\":" + name + ",\"meteos\":[]}";
		return expected;
	}

	public static String ArrayActivity(Set<Activity> activities) {
		String expected = "[";
		for (Activity a : activities) {
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

	public static String Site(Long id, String name, Location location, Set<Activity> activities) {
		name = addG(name);

		String strlocation = Location(location.getId(), location.getCity(),null,null,null);
		String stractivities = ArrayActivity(activities);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":" + strlocation + ",\"activities\":"
				+ stractivities + "}";
		return expected;
	}

	public static String Site(Long id, String name, Location location) {
		name = addG(name);

		String strlocation = Location(location.getId(), location.getCity(),null,null,null);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":" + strlocation
				+ ",\"activities\":[]}";
		return expected;
	}

	public static String Site(Long id, String name, Set<Activity> activities) {
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

	// ===========Role===============//
	public static String Role(Set<Role> roles) {
		String expected = "[";
		for (Role r : roles) {
			expected = expected + "\"" + r.name() + "\",";
		}
		expected = expected + "]";
		expected = expected.replaceAll(",]", "]");
		return expected;

	}
	// ===========EndRole===============//

	public static String Get(String entity) {
		String expected = "{\"data\":[" + entity + "]}";
		expected = expected.replaceAll("\\]\\]", "\\]");
		expected = expected.replaceAll("\\[\\[", "\\[");
		return expected;
	}

	// public static void main(String[] args) {
	// Set<Activity> set = new HashSet<Activity>();
	// set.add(new Sport("Merde"));
	// String strActivity = FactoryJSON.ArrayActivity(set);
	// System.out.println(strActivity);
	//
	// }
}
