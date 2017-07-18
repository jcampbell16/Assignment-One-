import java.io.File;
import java.util.ArrayList;

/**
 * CountryCatalogue is a class that go through files and make a catalogue for
 * country objects
 * 
 * @author Jazzy Campbell
 *
 */
public class CountryCatalogue {
	// Create private variables
	private final int DEFAULT_SIZE = 5;
	private final int NOT_FOUND = -1;
	private Country[] catalogue = new Country[DEFAULT_SIZE];
	private int numCountries = 0;

	/**
	 * First Constructor of CountryCatalogue. Used to open, read, and add file
	 * contents to catalogue
	 * 
	 * @param countryFile
	 * @param contFile
	 */
	public CountryCatalogue(String countryFile, String contFile) {
		File one = new File(countryFile);
		String pathone = one.getAbsolutePath(); // This way it finds exactly
												// where the file is
		File two = new File(contFile);
		String pathtwo = two.getAbsolutePath();
		// Open and add the first file. Remember to close.
		ThingToReadFile file = new ThingToReadFile(pathone);
		file.readLine();
		for (String line; (line = file.readLine()) != null;) {
			if (line.contains(",")) {
				String[] temp = line.split(",");
				String country = temp[0];
				int pop = Integer.valueOf(temp[1]);
				double area = Double.valueOf(temp[2]);
				Country holder = new Country(country, pop, area, "");
				addCountry(holder);
			}
		}
		file.close();

		// Open and add the second file. Remember to close.
		ThingToReadFile file2 = new ThingToReadFile(pathtwo);
		file2.readLine();
		for (String lines; (lines = file2.readLine()) != null;) {
			String[] temp = lines.split(",");
			String count = temp[0];
			String con = temp[1];
			for (int x = 0; x < numCountries; x++) {
				if (catalogue[x].getName().equals(count)) {
					catalogue[x].setContinent(con);
				}
			}
		}
		file2.close();
	}

	/**
	 * A second constructor to initialize the variables
	 */
	public CountryCatalogue() {
		numCountries = 0;
		catalogue = new Country[DEFAULT_SIZE];
	}

	/**
	 * Method adds person to the catalogue
	 * 
	 * @param cntry
	 *            country
	 */
	public void addCountry(Country cntry) {
		Country holder = cntry;
		if (catalogue.length == numCountries) {
			expandCapacity();
		}
		catalogue[numCountries] = holder;
		numCountries++;
	}

	/**
	 * Creates a new array to hold the countries with twice the capacity
	 */
	private void expandCapacity() {
		Country[] largerCatalogue = new Country[numCountries * 2];
		for (int x = 0; x < numCountries; x++) {
			largerCatalogue[x] = catalogue[x];
		}
		catalogue = largerCatalogue;
	}

	/**
	 * Returns the Country object from the entered index
	 * 
	 * @param index
	 * @return
	 */
	public Country getCountry(int index) {
		if (index < numCountries && index >= 0) {
			return catalogue[index];
		} else {
			return null;
		}
	}

	/**
	 * Calls the toString() method and prints catalogue
	 */
	public void printCountryCatalogue() {
		System.out.println("Country Catalogue: ");
		for (int x = 0; x < numCountries; x++) {
			System.out.print(catalogue[x].toString());
		}
	}

	/**
	 * Prints all countries that are in the stated continent
	 * 
	 * @param continent
	 */
	public void filterCountriesByContinent(String continent) {
		System.out.println(" \nCountries in " + continent + ":");
		for (int y = 0; y < numCountries; y++) {
			if (catalogue[y].getContinent().equals(continent)) {
				System.out.print(catalogue[y].toString());
			}
		}
	}

	/**
	 * Takes country and returns the index of the country in the catalogue
	 * 
	 * @param countryName
	 * @return
	 */
	public int searchCatalogue(String countryName) {
		int index = NOT_FOUND;
		if (countryName != null) {
			for (int x = 0; x < numCountries; x++) {
				String name = catalogue[x].getName();
				if (name.equals(countryName)) {
					index = x;
				}
			}
		}
		if (index == NOT_FOUND) {
			System.out.println(countryName + " was not found in catalogue.");
			return NOT_FOUND;
		}
		return index;
	}

	/**
	 * Receives the name of the country and removes it from the catalogue
	 * 
	 * @param countryName
	 */
	public void removeCountry(String countryName) {
		int index = searchCatalogue(countryName);
		if (index != NOT_FOUND) {
			catalogue[index] = catalogue[numCountries - 1];
			numCountries--;
			System.out.println(countryName + " has been successfully removed from the catalogue.");
		} else {
			System.out.println(countryName + " has not been successfully removed from the catalogue.");
		}
	}

	/**
	 * Receives the name of country and new population and sets it to the
	 * country object
	 * 
	 * @param countryName
	 * @param pop
	 */
	public void setPopulationOfACountry(String countryName, int pop) {
		int index = searchCatalogue(countryName);
		if (index != NOT_FOUND) {
			catalogue[index].setPopulation(pop);
			System.out.println("Population of " + countryName + " has been successfully set.");
		} else {
			System.out.println("Population of " + countryName + " has not been successfuly set.");
		}
	}

	/**
	 * Writes the catalogue content to the given file in the parameter
	 * 
	 * @param filename
	 */
	public void saveCountryCatalogue(String filename) {
		String file = filename;
		ThingToWriteFile holder = new ThingToWriteFile(file);
		for (int x = 0; x < numCountries; x++) {
			Country temp = catalogue[x];
			temp.writeToFile(holder);
		}
		holder.close();
	}

	/**
	 * A method to return the index of the country with the largest population
	 * 
	 * @return
	 */
	public int findCountryWithLargestPop() {
		int popHolder = 0;
		int index = NOT_FOUND;
		int pop = 0;
		for (int x = 0; x < numCountries; x++) {
			pop = catalogue[x].getPopulation();
			if (pop > popHolder) {
				index = x;
				popHolder = pop;
			}
		}
		return index;
	}

	/**
	 * A method to return the index of the country with the smallest area
	 * 
	 * @return
	 */
	public int findCountryWithSmallestArea() {
		double areaHolder = catalogue[0].getArea();
		int index = 0;
		for (int x = 0; x < numCountries; x++) {
			if (catalogue[x].getArea() < areaHolder) {
				areaHolder = catalogue[x].getArea();
				index = x;
			}
		}
		return index;
	}

	/**
	 * Method that prints out the population densitys that are in between the
	 * given low and high parameters
	 * 
	 * @param low
	 * @param high
	 */
	public void printCountriesFilterDensity(int low, int high) {
		System.out.println("Countries with a population density between " + low + " and " + high + ": ");
		for (int x = 0; x < numCountries; x++) {
			if (catalogue[x].getPopDensity() >= low && catalogue[x].getPopDensity() <= high) {
				System.out.println(catalogue[x].getName() + " in " + catalogue[x].getContinent()
						+ " has a population density of " + catalogue[x].getPopDensity());
			}
		}
	}

	public void findMostPopulousContinent() {
		ArrayList<String> continents = new ArrayList<String>();
		int numCont = 0;
		for (int x = 0; x < numCountries; x++) {
			String con = catalogue[x].getContinent();
			if (!continents.contains(con)) {
				continents.add(con);
				numCont++;
			}
		}
		int[] buckets = new int[continents.size()];
		for (int y = 0; y < numCountries; y++) {
			for (int z = 0; z < numCont; z++) {
				if (continents.get(z).equals(catalogue[y].getContinent())) {
					buckets[z] = buckets[z] + catalogue[y].getPopulation();
				}
			}
		}
		int popcheck = 0;
		int index = 0;
		for (int a = 0; a < buckets.length; a++) {
			if (buckets[a] > popcheck) {
				popcheck = buckets[a];
				index = a;
			}
		}
		System.out.println(
				"Continent with the largest population: " + continents.get(index) + ", with population " + popcheck);
	}
}
