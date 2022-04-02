package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.PropHelper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@CucumberOptions(features = "src/test/resources/features", glue = {"steps"}, tags = "@REG", plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, monochrome = true, publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {
    private static final Logger logger = LogManager.getLogger(TestRunner.class);

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Parameters({"browserName", "environment"})
    @BeforeTest
    public void setEnvironment(String browserName, @Optional("stage") String environment) {
        String dir = System.getProperty("user.dir") + "/Reports/Screenshots";
        if (Files.exists(Path.of(dir))) {
            File file = new File(dir);
            File[] files = file.listFiles();
            if (!(files == null)) {
                for (File f : files) {
                    if (f.getName().endsWith(".png")) {
                        boolean deleteStatus = f.delete();
                        if (!deleteStatus) {
                            logger.error("Failed to delete existing png files");
                        }
                    }
                }
            }
        }
        PropHelper.setBrowserName(browserName);
        PropHelper.setEnvironment(environment);
        logger.info("Environment is set");
    }


}