package com.exl.technical;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;

import com.exl.technical.pages.HomePage;
import com.exl.technical.pages.HomePage.TransportMode;

public class GoogleMapsTest extends JUnitTestBase {

	private HomePage homePage;

	@Before
	public void initPageObjects() {
		homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@Test
	public void testGoogleMaps() throws IOException {
		homePage.visit().searchLocationOnMap("San Francisco California");
		// Verify Co-ordinates
		Assert.assertTrue(homePage.getUrl().contains("@37.7576948,-122.4726194,13"));
		homePage.clickDirectionsButton().selectStartingPoint("Chico California")
				.selectTransportMode(TransportMode.DRIVING);
		// Verify 2 or more results are displayed after direction is mentioned
		Assert.assertTrue(homePage.getAllDirections().size() > 2);
		// Add the results to a file
		addResultListToFile(homePage.getAllDirections());
	}
	
	public void addResultListToFile(List<String> results) throws IOException {
		List<List<String>> splitResults = new ArrayList<List<String>>();
		for (String result : results) {
			splitResults.add(Arrays.asList(result.split("\n")));
		}
		
		File fileToBeWritten = new File("Details.txt");
		if (fileToBeWritten.createNewFile()) {
			System.out.println("File created: " + fileToBeWritten.getName() + " at: " + fileToBeWritten.getAbsolutePath());
		}
		
		FileWriter fileWriter = new FileWriter(fileToBeWritten);
		for (List<String> splitResult : splitResults) {
			fileWriter.write("Route Title: " + splitResult.get(2));
			fileWriter.write("\nDistance in Miles: " + splitResult.get(1));
			fileWriter.write("\nTravel Time: " + splitResult.get(0));
			fileWriter.write("\n============================================\n");
		}
		fileWriter.close();
	}
}
