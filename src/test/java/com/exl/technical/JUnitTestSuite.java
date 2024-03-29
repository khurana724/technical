package com.exl.technical;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ru.stqa.selenium.factory.WebDriverPool;

@RunWith(Suite.class)
@SuiteClasses({GoogleMapsTest.class})
public class JUnitTestSuite {

  @Rule
  public TestRule webDriverPool = new TestWatcher() {
    @Override
    protected void finished(Description description) {
      super.finished(description);
      WebDriverPool.DEFAULT.dismissAll();
    };
  };
}
