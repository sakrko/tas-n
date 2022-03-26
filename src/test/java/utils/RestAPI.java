package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class RestAPI {
    private Response response;

    public void request(String method) {
        RestAssured.baseURI = ConfigHelper.getPropValues("DDG_API_URL");
        RequestSpecification httpRequest = RestAssured.given();
        switch (method.toLowerCase()) {
            case "get":
                response = httpRequest.get();
                break;
            case "put":
                break;
            case "post":
                break;
            case "delete":
                break;
            case "patch":
                break;
            default:
                break;
        }
    }

    public void checkResponseStatusCode(String code) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(code));
    }
}
