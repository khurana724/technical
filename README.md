# Pre-Requisites:
- Chrome Browser installed as per chromedriver executable
- You might need to set the location of your chromedriver as per the placement of the binary in the repository in `\src\test\java\com\exl\technical\JUnitTestBase.java`

# Steps to Run:
- Do a maven install of all the dependencies using command `mvn -U clean install` either from Command prompt or Eclipse
- Navigate to file : `\src\test\java\com\exl\technical\GoogleMapsTest.java` and run the test method `testGoogleMaps()` using JUnit

# Outcome:
- The test will now run in Chrome browser only for now. Make sure to upgrade to latest chrome browser version.
- The assertions set in the framework currently are hard-assertions i.e. if any assertion fails, the test will cease to run at that point

# Pending Items:
- Screenshots
- Logging Reports

# Maven Archetype Used:
- webdriver-junit-archetype | Version: 4.5 | Group ID: ru.sqta.selenium