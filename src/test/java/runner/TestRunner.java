package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(features = "src/test/resources/features", glue = {"steps"}, tags = "@REG")
public class TestRunner extends AbstractTestNGCucumberTests {
    private static final Logger logger = LogManager.getLogger(TestRunner.class);

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeTest
    public void setEnvironment() {
        logger.info("Environment is set");
    }

}