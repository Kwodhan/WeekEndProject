package fr.istic.taa.weekEndProject.model;

public enum Wind {
	Leger(0, 9), Modére(10, 40), coupsdevent(61, 90), forcedetempête(91, 114), ForceOuragan(115, 500);

	private int min;
	private int max;

	private Wind(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public boolean between(int vitesse) {

		if (this.min <= vitesse &&  vitesse< this.max)
			return true;
		else
			return false;
	}
}
