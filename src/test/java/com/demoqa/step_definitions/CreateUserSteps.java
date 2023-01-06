package com.demoqa.step_definitions;

import com.demoqa.pages.ResponseApiPage;
import com.demoqa.utils.BookStoreApiUtils;
import com.demoqa.utils.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateUserSteps {

    Response response;
    String requestUserName;
    String requestPassword;
    String responseUserID;
    String responseUserName;
    int responseStatusCode;
    List<Map<String,Object>> books;

    ResponseApiPage responseApiPage = new ResponseApiPage();

    @When("user send a POST request to create user end point")
    public void user_send_a_post_request_to_create_user_end_point() {

       requestUserName= ConfigurationReader.getProperty("userName");
       //requestPassword= ConfigurationReader.getProperty("passWord");

        response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(responseApiPage.getRequestBody())
                .when().post("/"+ConfigurationReader.getProperty("apiUser"));
        response.prettyPrint();
    }
    @When("User captures status code, userID, username and books information")
    public void user_captures_status_code_user_id_username_and_books_information() {
        Map<Object,Object> responseMap= response.as(Map.class);
        responseUserID = (String) responseMap.get("userID");
        BookStoreApiUtils.storeInfoToFile(responseUserID);
        responseUserName= (String) responseMap.get("username");
        responseStatusCode= response.statusCode();
        books= (List<Map<String, Object>>) responseMap.get("books");
    }
    @Then("verifies status code username and userID is NOT null")
    public void verifies_status_code_username_and_user_id_is_not_null() {
        Assert.assertEquals(201,responseStatusCode);
        Assert.assertEquals(requestUserName,responseUserName);
        Assert.assertNotNull(responseUserID);
    }


}
