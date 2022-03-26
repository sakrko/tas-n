package steps;


import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.PropHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plinth.BrowserYard;
import plinth.PlinthInitializer;

import java.io.File;
import java.io.IOException;

public class TestInitializeHooks {
    public static Scenario sc;
    BrowserYard browserYard = new BrowserYard();
    private static final Logger logger = LogManager.getLogger(TestInitializeHooks.class);

    @Before
    public void beforeTest(Scenario scenario) {
        if (!scenario.getName().toLowerCase().contains("api")) {
            PlinthInitializer.setDriver(browserYard.createBrowser(PropHelper.getBrowserName()));
        }
        sc = scenario;
    }

    @After
    public void afterTest(Scenario scenario) throws IOException {
        if (scenario.isFailed() && !scenario.getName().toLowerCase().contains("api")) {
            addScreenshot();
        }
        if (PlinthInitializer.getDriver() != null) {
            browserYard.quitBrowser();
        }
    }

    public void addScreenshot() throws IOException {
        String screenShotName = sc.getName().replaceAll(" ", "_");
        File screenshot = ((TakesScreenshot) PlinthInitializer.getDriver()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        sc.attach(fileContent, "image/png", screenShotName);
    }

    public static void writeToReport(String msg) {
        ExtentCucumberAdapter.addTestStepLog(msg);
    }
}
