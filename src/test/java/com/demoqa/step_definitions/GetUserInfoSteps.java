package com.demoqa.step_definitions;

import com.demoqa.pages.ResponseApiPage;
import com.demoqa.utils.BookStoreApiUtils;
import com.demoqa.utils.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetUserInfoSteps {
    Response response;
    String requestUserName;
    String token;
    int responseStatusCode;
    String responseUserID;
    List<Map<String,Object>> books;
    String responseUserName;

    ResponseApiPage responseApiPage= new ResponseApiPage();

    @Given("Generate Token request is sent to related end point")
    public void generate_token_request_is_sent_to_related_end_point() {
        requestUserName= ConfigurationReader.getProperty("userName");

       Response responseToken= given().accept(ContentType.JSON)
               .and().contentType(ContentType.JSON)
               .and().body(responseApiPage.getRequestBody())
               .when().post("/"+ ConfigurationReader.getProperty("apiGenerateToken"));
        JsonPath jsonPath= responseToken.jsonPath();
        token= "Bearer"+jsonPath.getString("token");
        System.out.println("token = " + token);
    }
    @When("user sends GET request to receive user information")
    public void user_sends_get_request_to_receive_user_information() {
        String userID= BookStoreApiUtils.readFromFile();
        response= given().accept(ContentType.JSON)
                .and().header("Authorization",token)
                .when().get("/"+userID);
        response.prettyPrint();
    }
    @When("user captures status code, userID, username and books information for GET")
    public void user_captures_status_code_user_id_username_and_books_information_for_get() {
        Map<Object,Object> responseMap= response.as(Map.class);
        responseStatusCode= response.statusCode();
        responseUserID= (String) responseMap.get("userID");
        responseUserName= (String) responseMap.get("username");
        books= (List<Map<String, Object>>) responseMap.get("books");
    }
    @When("user sends GET request to receive all books information")
    public void user_sends_get_request_to_receive_all_books_information() {


    }
    @Then("Verifies status code, username and books information")
    public void verifies_status_code_username_and_books_information() {

    }

}
