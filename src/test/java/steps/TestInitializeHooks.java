package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plinth.BrowserYard;
import plinth.PlinthInitializer;

public class TestInitializeHooks extends PlinthInitializer {
    BrowserYard browserYard = new BrowserYard();
    private static final Logger logger = LogManager.getLogger(TestInitializeHooks.class);

    @Before
    public void beforeTest(Scenario scenario) {
        if (!scenario.getName().equals("")) {
           setDriver(browserYard.createBrowser("Chrome"));
        }
    }

    @After
    public void afterTest() {
        if (getDriver() != null) {
            browserYard.quitBrowser();
        }
    }
}
