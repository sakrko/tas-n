package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PropHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plinth.BrowserYard;
import plinth.PlinthInitializer;

public class TestInitializeHooks {
    BrowserYard browserYard = new BrowserYard();
    private static final Logger logger = LogManager.getLogger(TestInitializeHooks.class);

    @Before
    public void beforeTest(Scenario scenario) {
        if (!scenario.getName().equals("")) {
            PlinthInitializer.setDriver(browserYard.createBrowser(PropHelper.getBrowserName()));
        }
    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
        }
        if (PlinthInitializer.getDriver() != null) {
            browserYard.quitBrowser();
        }
    }
}
