package com.demoqa.step_definitions;

import com.demoqa.utils.BookItApiUtils;
import com.demoqa.utils.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class BookItStepDefs {

    static String token; //static for using everywhere (same package)
    static String globalEmail;
    static String globalPassword;
    static Response response;


    @Given("User logged Bookit api using {string} and {string}")
    public void user_logged_bookit_api_using_and(String email, String password) {
        token=BookItApiUtils.generateToken(email,password);
       globalEmail=email;
       globalPassword=password;
    }
    @When("User get the current user information from api")
    //send get request to retrieve current user information
    public void user_get_the_current_user_information_from_api() {
        String url=ConfigurationReader.getProperty("qa3api.url")+"/api/users/me";
        response= given().accept(ContentType.JSON)
                .and()
                .header("Authorization",token)
                .when()
                .get(url);
    }
    @Then("verify status code should be {int}")
    public void verify_status_code_should_be(int statusCode) {

      Assert.assertEquals(response.statusCode(),statusCode);
        System.out.println("statusCode = " + statusCode);
    }
    //java.lang.ClassCastException: class io.restassured.path.xml.XmlPath cannot be cast to class java.lang.String
    // (io.restassured.path.xml.XmlPath is in unnamed module of loader 'app'; java.lang.String is in module java.base of loader 'bootstrap')
    //sürekli bu mesaı alıyorum bu nedenle fail oluyor
}
