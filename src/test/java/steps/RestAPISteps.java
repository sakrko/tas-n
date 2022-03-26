package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.RestAPI;

public class RestAPISteps {
    RestAPI restAPI = new RestAPI();

    @Given("^the DDG API is queried with (.+)$")
    public void the_ddg_api_is_queried_with(String phrase) {
        restAPI.request("get");
    }

    @Then("^the response status code is (.+)$")
    public void the_response_status_code_is(String code) {
        restAPI.checkResponseStatusCode(code);
        TestInitializeHooks.writeToReport("Code is verfied");
    }
}
