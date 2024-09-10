package tests;

import helper.PayLoadHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utils.ApiUtils;
import Utils.PayloadLoader;
import Utils.ConfigLoader;

import java.io.IOException;

public class APITest {

    private SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigLoader.getBaseURI("jsonPlaceHolderURI");
        softAssert = new SoftAssert();
    }

   @Test(enabled = true)
    public void testCreatePost() throws IOException {

        // Load and replace placeholders in the JSON template
        String requestBody = PayloadLoader.loadPayload("createPost.json", PayLoadHelper.getCreateUserBody());

        // Perform the API request
        Response response = ApiUtils.sendPostRequest("/posts", requestBody);

        // Perform assertions

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201");
        softAssert.assertEquals(response.jsonPath().getInt("userId"), 2, "UserId should be 1");

        // Validate dynamic ID if returned
        String id = response.jsonPath().getString("id");
        softAssert.assertNotNull(id, "ID should not be null");
        softAssert.assertFalse(id.isEmpty(), "ID should not be empty");

        softAssert.assertAll();
    }


    @Test(enabled = true)
    public void testGetPost() {
        // Define the ID of the resource to retrieve
        String postId = "1";

        // Perform the GET request
        Response response = ApiUtils.sendGetRequest("/posts/" + postId);

        // Perform assertions
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        // Validate the response
        softAssert.assertEquals(response.jsonPath().getString("id"), "ID should not be null");
        softAssert.assertEquals(response.jsonPath().getInt("userId") > 0, "UserId should be greater than 0");
        softAssert.assertEquals(response.jsonPath().getString("title"), "Title should not be null");
        softAssert.assertEquals(response.jsonPath().getString("body"), "Body should not be null");

}
    @Test(enabled = true)
    public void testUpdatePost() throws IOException {

        // Load and replace placeholders in the JSON template
        String requestBody = PayloadLoader.loadPayload("updatePost.json", PayLoadHelper.getUpdateUserBody());

        // Perform the API request
        Response response = ApiUtils.sendPutRequest("/posts/1", requestBody); // Replace with the actual endpoint

        // Perform assertions
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        // Validate dynamic ID if returned
        String id = response.jsonPath().getString("id");
        softAssert.assertNotNull(id, "ID should not be null");
        softAssert.assertFalse(id.isEmpty(), "ID should not be empty");
    }

    @Test(enabled = true)
    public void testDeletePost(){

        // Define the ID of the resource to delete
        String postId = "1";

        // Perform the DELETE request
        Response response = ApiUtils.sendDeleteRequest("/posts/" + postId);

        softAssert.assertEquals(response.statusCode(),200,"Response code should be 200");
    }

}
