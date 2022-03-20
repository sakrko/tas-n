package steps;

import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.SamplePage;
import plinth.PlinthInitializer;
import runner.TestRunner;

public class SampleSteps extends PlinthInitializer {
    private static final Logger logger = LogManager.getLogger(SampleSteps.class);
    SamplePage samplePage = new SamplePage(getDriver());

    @Given("user is on ddg page")
    public void open_ddg_page() {
        samplePage.opneUrl("https://google.com");
        logger.info("Google has opened");
    }
}
