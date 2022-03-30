package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.skyscreamer.jsonassert.comparator.JSONComparator;
import org.testng.Assert;
import plinth.PlinthInitializer;
import utils.FileHelper;
import utils.RestAPI;

import java.io.File;

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
        Assert.assertEquals(restAPI.getStatusCode(), Integer.parseInt(statusCode));
    }

    //    And
    @And("^validate schema$")
    public void validate_schema() {
        String schemaFileName = jsonDataHelper.getDataMap().get("schemaName");
        File fileName = new File(FileHelper.getScenarioPath(schemaFileName));
        restAPI.getResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(fileName));
    }

    @And("^validate response body$")
    public void validate_response_body() throws JSONException {
        String responseBody = jsonDataHelper.getDataMap().get("responseBody");
        String expectedResponseBody = FileHelper.getFileToString("responseBody", responseBody);
        JSONComparator customisedJobComparator = new CustomComparator(JSONCompareMode.LENIENT, new Customization("id", (o1, o2) -> true), new Customization("createdAt", (o1, o2) -> true), new Customization("updatedAt", (o1, o2) -> true));
        JSONAssert.assertEquals(expectedResponseBody, restAPI.getResponseAsString(), customisedJobComparator);
    }
}
