package utils;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.skyscreamer.jsonassert.comparator.JSONComparator;
import org.testng.Assert;
import plinth.PlinthInitializer;

import java.io.File;
import java.util.HashMap;

public class RestAPI extends PlinthInitializer {
    private Response response = null;

    public void request(String method, String url, String body, String headers, String params) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.headers(getKeyValuePairsFromString(headers));

        if (!body.equals("")) {
            httpRequest.body(FileHelper.getFileToString("body", body));
        }
        switch (method.toLowerCase()) {
            case "get":
                httpRequest.params(getKeyValuePairsFromString(params));
                response = httpRequest.when().get(url);
                break;
            case "put":
                response = httpRequest.when().put(url);
                break;
            case "post":
                response = httpRequest.when().post(url);
                break;
            case "delete":
                response = httpRequest.when().delete(url);
                break;
            case "patch":
                response = httpRequest.when().patch(url);
                break;
            default:
                break;
        }
    }

    public void checkResponseStatusCode(String code) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(code));
    }

    public void validateSchema(String schemaFileName) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(FileHelper.getScenarioPath(schemaFileName))));
    }

    public void validateResponseBody(String responseBody) {
        String expectedResponseBody;
        String actualResponseBody = response.getBody().asString();
        JSONComparator customisedJobComparator = new CustomComparator(JSONCompareMode.LENIENT, new Customization("id", (o1, o2) -> true), new Customization("createdAt", (o1, o2) -> true), new Customization("updatedAt", (o1, o2) -> true));
        expectedResponseBody = FileHelper.getFileToString("responseBody", responseBody);
        try {
            JSONAssert.assertEquals(expectedResponseBody, actualResponseBody, customisedJobComparator);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getKeyValuePairsFromString(String str) {
        HashMap<String, String> obj = new HashMap<>();
        if (!str.equals("")) {
            String[] strList = str.split(",");
            for (String s : strList) {
                String[] sList = s.split("=");
                obj.put(sList[0], sList[1]);
            }
        }
        return obj;
    }
}
