package Engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static Util.Utility.jsonToString;

public class ApiRequest {

    // Helper method to prepare the request by setting the base URL and headers
    private static RequestSpecification prepareRequest(String baseUrl, Map<String, String> headers) {
        // Set the base URI
        RestAssured.baseURI = baseUrl;

        // Create a request specification
        RequestSpecification requestSpec = RestAssured.given();

        // Add headers if provided
        if (headers != null && !headers.isEmpty()) {
            requestSpec.headers(headers);
        }

        return requestSpec;
    }

    // Static method to perform a GET request with headers and return the full response
    public static Response GET(String baseUrl, String endpoint, Map<String, String> headers) {
        try {
            Response response = prepareRequest(baseUrl, headers)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();  // Perform the GET request and extract the response

            // Check if the response status code is 400 or higher
            if (response.getStatusCode() >= 400) {
                System.err.println("Error: " + response.getStatusLine());
            }

            return response;  // Return the response object

        } catch (Exception e) {
            // Log the exception message
            System.err.println("Request failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded GET method with default headers set to null
    public static Response GET(String baseUrl, String endpoint) {
        return GET(baseUrl, endpoint, null);  // Call the main GET method with null headers
    }

    // Static method to perform a POST request with headers and body
    public static Response POST(String baseUrl, String endpoint, Map<String, String> headers, Object body) {
        try {
            Response response = prepareRequest(baseUrl, headers)
                    .body(body)  // Add the request body
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .response();  // Perform the POST request and extract the response

            // Check if the response status code is 400 or higher
            if (response.getStatusCode() >= 400) {
                System.err.println("Error: " + response.getStatusLine());
            }

            return response;

        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded POST method with default headers set to null
    public static Response POST(String baseUrl, String endpoint, Object body) {
        return POST(baseUrl, endpoint, null, body);  // Call the main POST method with null headers
    }

    // Overloaded POST method with required header and the body as a map
    public static Response POST(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Object> bodyMap) {
        try {
            String body = jsonToString(bodyMap);
            return POST(baseUrl, endpoint, headers, body);  // Call the main POST method
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded POST method with default headers set to null and the body as a map
    public static Response POST(String baseUrl, String endpoint, Map<String, Object> bodyMap) {
        try {
            String body = jsonToString(bodyMap);
            return POST(baseUrl, endpoint, null, body);  // Call the main POST method with null headers
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded POST method with required header and the body as a JSON file
    public static Response POST(String baseUrl, String endpoint, Map<String, String> headers, Path dataFile) {
        try {
            // Change the JSON file to string
            String body = Files.readString(dataFile);
            return POST(baseUrl, endpoint, headers, body);  // Call the main POST method
        } catch (IOException e) {
            System.err.println("File reading failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded POST method with default headers set to null and the body as a JSON file
    public static Response POST(String baseUrl, String endpoint, Path dataFile) {
        try {
            // Change the JSON file to string
            String body = Files.readString(dataFile);
            return POST(baseUrl, endpoint, null, body);  // Call the main POST method with null headers
        } catch (IOException e) {
            System.err.println("File reading failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Static method to perform a PUT request with headers and body
    public static Response PUT(String baseUrl, String endpoint, Map<String, String> headers, Object body) {
        try {
            Response response = prepareRequest(baseUrl, headers)
                    .body(body)  // Add the request body
                    .when()
                    .put(endpoint)
                    .then()
                    .extract()
                    .response();  // Perform the PUT request and extract the response

            // Check if the response status code is 400 or higher
            if (response.getStatusCode() >= 400) {
                System.err.println("Error: " + response.getStatusLine());
            }

            return response;

        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PUT method with default headers set to null
    public static Response PUT(String baseUrl, String endpoint, Object body) {
        return PUT(baseUrl, endpoint, null, body);  // Call the main PUT method with null headers
    }

    // Overloaded PUT method with required header and the body as a map
    public static Response PUT(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Object> bodyMap) {
        try {
            String body = jsonToString(bodyMap);
            return PUT(baseUrl, endpoint, headers, body);  // Call the main PUT method
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PUT method with default headers set to null and the body as a map
    public static Response PUT(String baseUrl, String endpoint, Map<String, Object> bodyMap) {
        try {
            String body = jsonToString(bodyMap);
            return PUT(baseUrl, endpoint, null, body);  // Call the main PUT method with null headers
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PUT method with required header and the body as a JSON file
    public static Response PUT(String baseUrl, String endpoint, Map<String, String> headers, Path dataFile) {
        try {
            // Change the JSON file to string
            String body = Files.readString(dataFile);
            return PUT(baseUrl, endpoint, headers, body);  // Call the main PUT method
        } catch (IOException e) {
            System.err.println("File reading failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PUT method with default headers set to null and the body as a JSON file
    public static Response PUT(String baseUrl, String endpoint, Path dataFile) {
        try {
            // Change the JSON file to string
            String body = Files.readString(dataFile);
            return PUT(baseUrl, endpoint, null, body);  // Call the main PUT method with null headers
        } catch (IOException e) {
            System.err.println("File reading failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Static method to perform a PATCH request with headers and body
    public static Response PATCH(String baseUrl, String endpoint, Map<String, String> headers, Object body) {
        try {
            Response response = prepareRequest(baseUrl, headers)
                    .body(body)  // Add the request body
                    .when()
                    .patch(endpoint)
                    .then()
                    .extract()
                    .response();  // Perform the PATCH request and extract the response

            // Check if the response status code is 400 or higher
            if (response.getStatusCode() >= 400) {
                System.err.println("Error: " + response.getStatusLine());
            }

            return response;

        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PATCH method with default headers set to null
    public static Response PATCH(String baseUrl, String endpoint, Object body) {
        return PATCH(baseUrl, endpoint, null, body);  // Call the main PATCH method with null headers
    }

    // Overloaded PATCH method with required header and the body as a map
    public static Response PATCH(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Object> bodyMap) {
        try {
            String body = jsonToString(bodyMap);
            return PATCH(baseUrl, endpoint, headers, body);  // Call the main PATCH method
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PATCH method with default headers set to null and the body as a map
    public static Response PATCH(String baseUrl, String endpoint, Map<String, Object> bodyMap) {
        try {
            String body = jsonToString(bodyMap);
            return PATCH(baseUrl, endpoint, null, body);  // Call the main PATCH method with null headers
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PATCH method with required header and the body as a JSON file
    public static Response PATCH(String baseUrl, String endpoint, Map<String, String> headers, Path dataFile) {
        try {
            // Change the JSON file to string
            String body = Files.readString(dataFile);
            return PATCH(baseUrl, endpoint, headers, body);  // Call the main PATCH method
        } catch (IOException e) {
            System.err.println("File reading failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded PATCH method with default headers set to null and the body as a JSON file
    public static Response PATCH(String baseUrl, String endpoint, Path dataFile) {
        try {
            // Change the JSON file to string
            String body = Files.readString(dataFile);
            return PATCH(baseUrl, endpoint, null, body);  // Call the main PATCH method with null headers
        } catch (IOException e) {
            System.err.println("File reading failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Static method to perform a DELETE request with headers
    public static Response DELETE(String baseUrl, String endpoint, Map<String, String> headers) {
        try {
            Response response = prepareRequest(baseUrl, headers)
                    .when()
                    .delete(endpoint)
                    .then()
                    .extract()
                    .response();  // Perform the DELETE request and extract the response

            // Check if the response status code is 400 or higher
            if (response.getStatusCode() >= 400) {
                System.err.println("Error: " + response.getStatusLine());
            }

            return response;

        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            return null;  // Return null in case of failure
        }
    }

    // Overloaded DELETE method with default headers set to null
    public static Response DELETE(String baseUrl, String endpoint) {
        return DELETE(baseUrl, endpoint, null);  // Call the main DELETE method with null headers
    }
}
