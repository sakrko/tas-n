package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import plinth.PlinthInitializer;

public class RestAPI extends PlinthInitializer {
    private Response response;

    public void request(String method, String url) {
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        switch (method.toLowerCase()) {
            case "get":
                response = httpRequest.get();
                System.out.println(response.prettyPrint());
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
