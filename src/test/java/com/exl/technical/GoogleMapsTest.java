package com.exl.technical;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;

import com.exl.technical.pages.HomePage;
import com.exl.technical.pages.HomePage.TransportMode;
import com.exl.technical.helpers.GoogleMapsHelpers;

public class GoogleMapsTest extends JUnitTestBase {

	private HomePage homePage;
	public GoogleMapsHelpers googleMapsHelpers = new GoogleMapsHelpers();

	@Before
	public void initPageObjects() {
		homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@Test
	public void testGoogleMaps() throws IOException {
		homePage.visit().searchLocationOnMap("San Francisco California");
		// Verify Co-ordinates
		Assert.assertTrue(homePage.getUrl().contains("@37.757815,-122.5076403"));
		homePage.clickDirectionsButton().selectStartingPoint("Chico California")
				.selectTransportMode(TransportMode.DRIVING);
		// Verify 2 or more results are displayed after direction is mentioned
		Assert.assertTrue(homePage.getAllDirections().size() > 2);
		// Add the results to a file
		googleMapsHelpers.addResultListToFile(homePage.getAllDirections());
	}
}
