package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Hook;
import plinth.PlinthInitializer;
import utils.RestAPI;

public class RestAPISteps extends PlinthInitializer {
    RestAPI restAPI = new RestAPI();

    //    Given
    @Given("^test data is loaded from (.+)$")
    public void test_data_is_loaded_from(String testcase) {
        setCM(jsonDataHelper.loadTestDataScenario(testcase.trim()));
        Hooks.writeToReport(jsonDataHelper.getDataMap().toString());

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
        Hooks.writeToReport(jsonDataHelper.getDataMap().toString());
    }

    //    Then
    @Then("^status code is (.+)$")
    public void status_code_is(String statusCode) {
        Hooks.writeToReport(jsonDataHelper.getDataMap().toString());
        restAPI.checkResponseStatusCode(statusCode);
    }

    //    And
    @And("^validate schema$")
    public void validate_schema() {
        Hooks.writeToReport(jsonDataHelper.getDataMap().toString());
        restAPI.validateSchema(jsonDataHelper.getDataMap().get("schemaName"));
    }

    @And("^validate response body$")
    public void validate_response_body() {
        Hooks.writeToReport(jsonDataHelper.getDataMap().toString());
        String responseBody = jsonDataHelper.getDataMap().get("responseBody");
        restAPI.validateResponseBody(responseBody);
    }
}
