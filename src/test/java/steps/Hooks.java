package steps;


import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.JsonDataHelper;
import utils.PropHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plinth.BrowserYard;
import plinth.PlinthInitializer;

import java.io.File;
import java.io.IOException;

public class Hooks {
    public static Scenario sc;
    BrowserYard browserYard = new BrowserYard();
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void beforeTest(Scenario scenario) {
        long threadId = Thread.currentThread().getId();
        logger.info("Current Thread is running on: " + threadId);
        PlinthInitializer.setS(scenario);
        if (!scenario.getName().toLowerCase().contains("api")) {
            PlinthInitializer.setBrowser(browserYard.createBrowser(PropHelper.getBrowserName()));
        }
//        sc = scenario;
    }

    @After
    public void afterTest(Scenario scenario) throws IOException {
        if (scenario.isFailed() && !scenario.getName().toLowerCase().contains("api")) {
            addScreenshot();
        }
        if (PlinthInitializer.getBrowser() != null) {
            browserYard.quitBrowser();
        }
    }

    public static synchronized void addScreenshot() throws IOException {
        String screenShotName = PlinthInitializer.getS().getName().replaceAll(" ", "_");
        File screenshot = ((TakesScreenshot) PlinthInitializer.getBrowser()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        PlinthInitializer.getS().attach(fileContent, "image/png", screenShotName);
    }

    public static void writeToReport(String msg) {
        ExtentCucumberAdapter.addTestStepLog(msg);
    }
}
