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
    JsonPath jsonPath;

    @When("User sends GET request to receive all books info")
    public void user_sends_get_request_to_receive_all_books_info() {
        response = given().accept(ContentType.JSON)
                    .get("/BookStore/v1/Books");
    }
    @Then("Verifies that we have {int} books in response")
    public void verifies_that_we_have_books_in_response(int amount) {

        jsonPath = response.jsonPath();

        List<Object> books = jsonPath.getList("books");

        System.out.println("books.size() = " + books.size());

        Assert.assertEquals(amount,books.size());
    }

    @When("User sends GET request to receive first book info")
    public void userSendsGETRequestToReceiveFirstBookInfo() {
        response=given().queryParam("ISBN","9781449337711")
                .accept(ContentType.JSON).get("/BookStore/v1/Book");
        //response.prettyPrint();
    }
//    @Then("Verify that we have correct info about fisrt book which we selected")
//    public void verify_that_we_have_correct_info_about_fisrt_book_which_we_selected() {
//      jsonPath=response.jsonPath();
//        String title= jsonPath.getString("title");
//        System.out.println("title = " + title);
//
//        String subTitle=jsonPath.getString("subTitle");
//        System.out.println("subTitle = " + subTitle);
//
//        String author=jsonPath.getString("author");
//        System.out.println("author = " + author);
//
//        String publish_date=jsonPath.getString("publish_date");
//        System.out.println("publish_date = " + publish_date);
//
//        String publisher=jsonPath.getString("publisher");
//        System.out.println("publisher = " + publisher);
//
//        String pages=jsonPath.getString("pages");
//        System.out.println("pages = " + pages);
//
//        String description=jsonPath.getString("description");
//        System.out.println("description = " + description);
//
//        String website=jsonPath.getString("website");
//        System.out.println("website = " + website);
//
//        Assert.assertEquals("Designing Evolvable Web APIs with ASP.NET",title);
//        Assert.assertEquals("238",pages);
//        Assert.assertEquals("Glenn Block et al.",author);
//    }

    @Then("Verify that we have correct info about fisrt book which we selected {string} {string}")
    public void verifyThatWeHaveCorrectInfoAboutFisrtBookWhichWeSelected(String key, String value) {
        jsonPath=response.jsonPath();
        value= jsonPath.getString(key);
        System.out.println(key+" = "+value);

        Assert.assertEquals(jsonPath.getString(key),value); //emin değilim doğru bir assertion mantığı olmuş mu Oscar?
    }
    @When("user sends GET reguest with a param")
    public void user_sends_get_reguest_with_a_param() {
        response= given().accept(ContentType.JSON)
                .queryParam("ISBN","9781449325862")
                .get("/BookStore/v1/Book");
        // response.prettyPrint();
    }
    @Then("Verify {string} book's title {string}")
    public void verifyBookS(String number, String title) {
        response= given().accept(ContentType.JSON)
                .get("/BookStore/v1/Books");

        jsonPath=response.jsonPath();
        title= jsonPath.getString("books["+number+"].title");
        System.out.println("title "+number+" = " + title);
        Assert.assertTrue(response.body().asString().contains(title));

    }
    ////////////////ZIPPOPOTAM.US//////////////////////////////

    @When("User sends GET request to receive info by endpoint")
    public void user_sends_get_request_to_receive_info_by_endpoint() {
        response=given().accept(ContentType.JSON)
                .get("TR/71400");
        response.prettyPrint();
    }
    @Then("verify response body is correct")
    public void verify_response_body_is_correct() {
        jsonPath=response.jsonPath();
        String longitude = jsonPath.getString("places[8].longitude");
        System.out.println("longitude = " + longitude);
        //String placeName= jsonPath.getString("places[8].place name");
       // System.out.println("placeName = " + placeName);
        Assert.assertEquals("34.1667",longitude);
        Assert.assertTrue("Verify place name is that",jsonPath.getString("places[8]").contains("Kaletepe Mahallesi"));
    }
}
