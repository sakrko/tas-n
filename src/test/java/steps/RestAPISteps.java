package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import plinth.PlinthInitializer;
import utils.RestAPI;

public class RestAPISteps extends PlinthInitializer {
    RestAPI restAPI = new RestAPI();

    //    Given
    @Given("^test data is loaded from (.+)$")
    public void test_data_is_loaded_from(String testcase) {
        setCM(jsonDataHelper.loadTestDataScenario(testcase.trim()));
    }

    //    When
    @When("^user makes an api call$")
    public void user_makes_an_api_call() {
        String method = jsonDataHelper.getDataMap().get("method");
        String url = configHelper.getConfigProp("REQRES_URL") + jsonDataHelper.getDataMap().get("url");
        String body = jsonDataHelper.getDataMap().get("body");
        String headers = jsonDataHelper.getDataMap().get("headers");
        String params = jsonDataHelper.getDataMap().get("params");
        restAPI.request(method, url, body, headers, params);
    }

    //    Then
    @Then("^status code is (.+)$")
    public void status_code_is(String statusCode) {
        restAPI.checkResponseStatusCode(statusCode);
    }

    //    And
    @And("^validate schema$")
    public void validate_schema() {
        restAPI.validateSchema(jsonDataHelper.getDataMap().get("schemaName"));
    }

    @And("^validate response body$")
    public void validate_response_body() {
        String responseBody = jsonDataHelper.getDataMap().get("responseBody");
        restAPI.validateResponseBody(responseBody);
    }
}
