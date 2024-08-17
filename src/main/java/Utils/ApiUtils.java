package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

    public class ApiUtils {

        // Method for GET request
        public static Response sendGetRequest(String endpoint) {
            return RestAssured.given().log().all()
                    .when()
                    .get(endpoint)
                    .then()
                    .log()
                    .all()
                    .extract().response();
        }

        // Method for POST request
        public static Response sendPostRequest(String endpoint, String requestBody) {
            return RestAssured.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(endpoint)
                    .then()
                    .log().all()
                    .extract().response();
        }

        // Method for PUT request
        public static Response sendPutRequest(String endpoint, String requestBody) {
            return RestAssured.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .put(endpoint)
                    .then()
                    .log()
                    .all()
                    .extract().response();

    }

}
