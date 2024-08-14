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
        RestAssured.baseURI = ConfigLoader.getBaseURI();
        softAssert = new SoftAssert();
    }

    @Test
    public void testCreatePost() throws IOException {


        // Load and replace placeholders in the JSON template
        String requestBody = PayloadLoader.loadPayload("createPost.json", PayLoadHelper.getCreateUserBody());

        // Perform the API request
        Response response = ApiUtils.sendPostRequest("/posts", requestBody);

        // Perform assertions

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201");

        softAssert.assertEquals(response.jsonPath().getInt("userId"), 1, "UserId should be 1");

        // Validate dynamic ID if returned
        String id = response.jsonPath().getString("id");
        softAssert.assertNotNull(id, "ID should not be null");
        softAssert.assertFalse(id.isEmpty(), "ID should not be empty");

        softAssert.assertAll();
    }
}
