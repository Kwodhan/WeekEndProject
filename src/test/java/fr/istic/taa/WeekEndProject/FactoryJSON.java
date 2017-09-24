package fr.istic.taa.WeekEndProject;

import java.util.Set;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;

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
		String strlocations = "";
		for (Location l : locations) {
			strlocations = strlocations + Location(l.getId(), l.getCity());
		}
		String stractivities = "";
		for (AbstractActivity a : activities) {
			stractivities = stractivities + Activity(a.getId(), a.getName(), a.getType());
		}
		String expected = "{\"id\":" + id + ",\"firstName\":" + firstName + "" + ",\"lastName\":" + lastName + ""
				+ ",\"emailAddress\":" + email + "" + ",\"homes\":[" + strlocations + "],\"activities\":["
				+ stractivities + "]}";
		return expected;
	}

	public static String PersonActivity(Long id, String firstName, String lastName, String email,
			Set<AbstractActivity> activities) {
		firstName = addG(firstName);
		lastName = addG(lastName);
		email = addG(email);
		String stractivities = "";
		for (AbstractActivity a : activities) {
			stractivities = stractivities + Activity(a.getId(), a.getName(), a.getType());
		}
		String expected = "{\"id\":" + id + ",\"firstName\":" + firstName + "" + ",\"lastName\":" + lastName + ""
				+ ",\"emailAddress\":" + email + "" + ",\"homes\":[],\"activities\":[" + stractivities + "]}";
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

	// ===========EndLocation===============//

	// ===========Activity===============//
	public static String Activity(Long id, String name, String type) {
		name = addG(name);
		type = addG(type);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"type\":" + type + ",\"meteos\":[]}";
		return expected;
	}
	// ===========EndActivity===============//

	// ===========Site===============//
	public static String Site(Long id, String name) {
		name = addG(name);

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":null,\"activity\":null}";
		return expected;
	}

	public static String Site(Long id, String name, Location location, AbstractActivity activity) {
		name = addG(name);

		String strlocation = Location(location.getId(), location.getCity());
		String stractivity = Activity(activity.getId(), activity.getName(), activity.getType());

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":" + strlocation + ",\"activity\":"
				+ stractivity + "}";
		return expected;
	}

	public static String Site(Long id, String name, Location location) {
		name = addG(name);

		String strlocation = Location(location.getId(), location.getCity());

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":" + strlocation
				+ ",\"activity\":null}";
		return expected;
	}

	public static String Site(Long id, String name, AbstractActivity activity) {
		name = addG(name);

		String stractivity = Activity(activity.getId(), activity.getName(), activity.getType());

		String expected = "{\"id\":" + id + ",\"name\":" + name + ",\"location\":null,\"activity\":" + stractivity
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

}
