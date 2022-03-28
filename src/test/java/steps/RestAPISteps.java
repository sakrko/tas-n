package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import plinth.PlinthInitializer;
import utils.RestAPI;

public class RestAPISteps extends PlinthInitializer {
    RestAPI restAPI = new RestAPI();

    @Given("^the DDG API is queried with (.+)$")
    public void the_ddg_api_is_queried_with(String phrase) {
        restAPI.request("get", "");
    }

    @Then("^the response status code is (.+)$")
    public void the_response_status_code_is(String code) {
        restAPI.checkResponseStatusCode(jsonDataHelper.getDataMap().get("code"));
        TestInitializeHooks.writeToReport("Code is verfied");
    }

    @Given("test data is loaded")
    public void testDataIsLoaded() {
        jsonDataHelper.loadTestDataScenario("TC01");
    }

    //    Given
    @Given("^test data is loaded from (.+)$")
    public void test_data_is_loaded_from(String testcase) {
        jsonDataHelper.loadTestDataScenario(testcase);
    }

    //    When
    @When("^user makes an api call$")
    public void user_makes_an_api_call() {
        String method = jsonDataHelper.getDataMap().get("method");
        String url = configHelper.getPropValues("DEMOQA_URL") + jsonDataHelper.getDataMap().get("url");
        restAPI.request(method, url);
    }

    //    Then
    @Then("^status code is (.+)$")
    public void status_code_is(String statusCode) {
        restAPI.checkResponseStatusCode(statusCode);
    }
}
