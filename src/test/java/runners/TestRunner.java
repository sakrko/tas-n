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

@Parameters({"tag"})
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
        PropHelper.setBrowserName(browserName);
        PropHelper.setEnvironment(environment);
        logger.info("Environment is set");
    }


}