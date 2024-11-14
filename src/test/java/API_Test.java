import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static Engine.ApiRequest.*;
import static Util.Utility.returnDataPath;
import static Util.Utility.jsonToString;

public class API_Test {

    private String BASE_URL = "https://reqres.in";

    @Test
    public void testLogin(){

    }

    @Test
    public void getRequest() {

        // Perform the GET request and get the response
        Response response = GET(
                BASE_URL,
                "/api/users?page=2");

        // Print the response body
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.jsonPath().getString(
                "data[0].email"), "michael.lawson@reqres.in");
    }

    @Test
    public void postRequestWithMapBody() throws JsonProcessingException {

        // Using a Map as the body
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", "morpheus");
        bodyMap.put("job", "leader");


        // Optionally, create headers (if needed)
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Perform the POST request and get the response
        Response response = POST(
                BASE_URL,
                "/api/users",
                headers,
                bodyMap);
        // Print the response body
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");

    }

    @Test
    public void postRequestWithJsonFileBody() throws IOException {

        //get the json file directory
        Path body = Path.of(returnDataPath("searchForProduct.json")); // Update this path


        // Optionally, create headers (if needed)
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Perform the POST request and get the response
        Response response = POST(
                BASE_URL,
                "/api/users",
                headers,
                body);

        // Print the response body
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");

    }

    @Test
    public void putRequestWithJsonFileBody() throws IOException {

        // get the json file
        Path body = Path.of(returnDataPath("searchForProduct.json"));

        // Optionally, create headers (if needed)
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Perform the POST request and get the response
        Response response = PUT(
                BASE_URL,
                "/api/users/2",
                headers,
                body);

        // Print the response body
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");

    }

    @Test
    public void deleteRequest() {

        // Optionally, create headers (if needed)
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Perform the POST request and get the response
        Response response = DELETE(
                BASE_URL,
                "/api/users/2",
                headers);

        // assert on status code
        Assert.assertTrue(response.getStatusCode() == 204);
    }

}
