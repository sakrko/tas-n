package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.PropHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

@Test
@CucumberOptions(features = "src/test/resources/features", glue = {"steps"}, tags = "@REG")
public class TestRunner extends AbstractTestNGCucumberTests {
    private static final Logger logger = LogManager.getLogger(TestRunner.class);

    @DataProvider(parallel = false)
    @Override
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