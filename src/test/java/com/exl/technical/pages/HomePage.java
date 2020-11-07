package com.exl.technical.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Sample page
 */
public class HomePage extends Page {
	String pageUrl = "https://www.google.com/maps/";

	protected By searchTextBoxBy = By.id("searchboxinput");
	protected By searchSuggestionsBy = By.cssSelector("div.suggestions div.suggest-left-cell");
	protected By selectedSearchHeaderTitleBy = By.className("section-hero-header-title-title");
	protected By directionsButtonBy = By.cssSelector("div[data-value='Directions'] button.section-action-chip-button");
	protected By startingPointInDirectionsBy = By
			.cssSelector("div[id*='directions-searchbox'] div#sb_ifc51:nth-child(1) input.tactile-searchbox-input");
	protected By directionFirstResultBy = By.id("section-directions-trip-0");

	@FindBy(how = How.ID, using = "searchboxinput")
	@CacheLookup
	protected WebElement searchTextBox;

	@FindBy(how = How.CSS, using = "div.suggestions div.suggest-left-cell")
	protected List<WebElement> searchSuggestionsList;

	@FindBy(how = How.CSS, using = "div[data-value='Directions'] button.section-action-chip-button")
	protected WebElement directionsButton;

	@FindBy(how = How.CSS, using = "div[id*='directions-searchbox'] div#sb_ifc51:nth-child(1) input.tactile-searchbox-input")
	protected WebElement startingPointInDirectionsTextBox;

	@FindBy(how = How.XPATH, using = "//img[@aria-label='Driving']/parent::button")
	protected static WebElement drivingModeRadioButton;

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	@SuppressWarnings("unchecked")
	public HomePage visit() {
		driver.get(pageUrl);
		waitForVisibility(searchTextBoxBy);
		return this;
	}

	public HomePage searchLocationOnMap(String searchLocation) {
		searchTextBox.sendKeys(searchLocation);
		waitForVisibility(searchSuggestionsBy);
		for (WebElement searchSuggestion : searchSuggestionsList) {
			if (searchSuggestion.getText().contains(searchLocation)) {
				searchSuggestion.click();
				break;
			}
		}
		waitForVisibility(directionsButtonBy);
		return this;
	}

	public HomePage clickDirectionsButton() {
		directionsButton.click();
		waitForVisibility(startingPointInDirectionsBy);
		return this;
	}

	public HomePage selectStartingPoint(String startingPoint) {
		startingPointInDirectionsTextBox.sendKeys(startingPoint);
		waitForVisibility(searchSuggestionsBy);
		for (WebElement searchSuggestion : searchSuggestionsList) {
			if (searchSuggestion.getText().contains(startingPoint)) {
				searchSuggestion.click();
				break;
			}
		}
		waitForVisibility(directionFirstResultBy);
		return this;
	}

	public HomePage selectTransportMode(TransportMode transportMode) {
		transportMode.getElement().click();
		waitForVisibility(directionFirstResultBy);
		return this;
	}

	public List<String> getAllDirections() {
		List<WebElement> directionResults = driver.findElements(By.cssSelector("div[id*='section-directions-trip-']"));
		return directionResults.stream().map(r -> r.getText()).collect(Collectors.toList());
	}

	public enum TransportMode {
		DRIVING("driving", drivingModeRadioButton);

		private String mode;
		private WebElement element;

		TransportMode(String mode, WebElement element) {
			this.setMode(mode);
			this.setElement(element);
		}

		public String getMode() {
			return mode;
		}

		public TransportMode setMode(String mode) {
			this.mode = mode;
			return this;
		}

		public WebElement getElement() {
			return element;
		}

		public TransportMode setElement(WebElement element) {
			this.element = element;
			return this;
		}
	}
}
