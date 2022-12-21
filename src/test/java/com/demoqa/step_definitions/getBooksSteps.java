package com.demoqa.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

public class getBooksSteps {

    Response response;

    @When("User sends GET request to receive all books info")
    public void user_sends_get_request_to_receive_all_books_info() {
        response = given().accept(ContentType.JSON)
                    .get("/BookStore/v1/Books");
    }
    @Then("Verifies that we have {int} books in response")
    public void verifies_that_we_have_books_in_response(int amount) {

        JsonPath jsonPath = response.jsonPath();

        List<Object> books = jsonPath.getList("books");

        System.out.println("books.size() = " + books.size());

        Assert.assertEquals(amount,books.size());
    }

}
