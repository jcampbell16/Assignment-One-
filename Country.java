/**
 * Class Country is to create an object that holds all information on a specific
 * country
 * 
 * @author Jazzy Campbell
 *
 */
public class Country {
	// Make the private instance variables
	private String name;
	private int population;
	private double area;
	private String continent;

	/**
	 * Constructor to create a Country object with the given parameters
	 * 
	 * @param name
	 * @param population
	 * @param area
	 * @param continent
	 */
	public Country(String name, int population, double area, String continent) {
		this.name = name;
		this.population = population;
		this.area = area;
		this.continent = continent;
	}

	/**
	 * Return method for country name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return method for population
	 * 
	 * @return
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * Return method for area
	 * 
	 * @return
	 */
	public double getArea() {
		return area;
	}

	/**
	 * Return method for continent
	 * 
	 * @return
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * Return method for population density
	 * 
	 * @return
	 */
	public double getPopDensity() {
		double temp = population / area;
		return temp;
	}

	/**
	 * A method to set the population of a country to given parameter value
	 * 
	 * @param pop
	 */
	public void setPopulation(int pop) {
		this.population = pop;
	}

	/**
	 * A method to set the continent of a country to given String parameter
	 * 
	 * @param cont
	 */
	public void setContinent(String cont) {
		this.continent = cont;
	}

	/**
	 * A method for writing to a ThingToWriteFile parameter
	 * 
	 * @param out
	 */
	public void writeToFile(ThingToWriteFile out) {
		out.writeLine(name + ", " + continent + ", " + population + ", " + (population / area) + " \n");
	}

	/**
	 * A method for printing out country details
	 */
	public void printCountryDetails() {
		String fs = name + " is located in " + continent + " has a population of " + population + ", an area of " + area
				+ ", and has a population density of " + (population / area);
		System.out.println(fs);
	}

	/**
	 * A toString method to print out a specific line
	 */
	public String toString() {
		String s = name + " in " + continent + "\n";
		return s;
	}
}
