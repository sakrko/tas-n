package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import plinth.PlinthInitializer;

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

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public Response getResponse() {
        return response;
    }

    public String getResponseAsString() {
        return response.getBody().asString();
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
