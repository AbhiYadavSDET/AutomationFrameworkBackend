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
import java.util.concurrent.TimeUnit;

public class APITest {

    private SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigLoader.getBaseURI("jsonPlaceHolderURI");
        softAssert = new SoftAssert();
    }

   @Test(enabled = true, priority = 1)
    public void testCreatePost() throws IOException {
        System.out.println(" =============== Starting Create Post API to Test ================");

        // Load and replace placeholders in the JSON template
        String requestBody = PayloadLoader.loadPayload("createPost.json", PayLoadHelper.getCreateUserBody());

        // Perform the API request
        Response response = ApiUtils.sendPostRequest("/posts", requestBody);

        // Perform assertions

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201");
        response.timeIn(TimeUnit.SECONDS);

        softAssert.assertEquals(response.jsonPath().getInt("userId"), 2, "UserId should be 1");

        // Validate dynamic ID if returned
        String id = response.jsonPath().getString("id");
        softAssert.assertNotNull(id, "ID should not be null");
        softAssert.assertFalse(id.isEmpty(), "ID should not be empty");

        softAssert.assertAll();
       System.out.println(" =============== End Create Post API to Test ================");
    }


    @Test(enabled = true,priority = 2)
    public void testGetPost() {
        System.out.println(" =============== Starting Get Post API to Test ================");
        // Define the ID of the resource to retrieve
        String postId = "1";

        // Perform the GET request
        Response response = ApiUtils.sendGetRequest("/posts/" + postId);

        // Perform assertions
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        // Validate the response
        softAssert.assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
        softAssert.assertTrue(response.jsonPath().getInt("userId") > 0, "UserId should be greater than 0");
        softAssert.assertNotNull(response.jsonPath().getString("title"), "Title should not be null");
        softAssert.assertNotNull(response.jsonPath().getString("body"), "Body should not be null");

        // Assert all
        softAssert.assertAll();
        System.out.println(" =============== End Get Post API to Test ================");
    }

    @Test(enabled = true,priority = 3)
    public void testUpdatePost() throws IOException {
        System.out.println(" =============== Starting Update Post API to Test ================");

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
        softAssert.assertAll();
        System.out.println(" =============== End Create Post API to Test ================");
    }

    @Test(enabled = true,priority = 4)
    public void testDeletePost(){
        System.out.println(" =============== Starting Delete Post API to Test ================");
        // Define the ID of the resource to delete
        String postId = "1";

        // Perform the DELETE request
        Response response = ApiUtils.sendDeleteRequest("/posts/" + postId);

        softAssert.assertEquals(response.statusCode(),200,"Response code should be 200");

        softAssert.assertAll();
        System.out.println(" =============== End Delete Post API to Test ================");
    }

}
