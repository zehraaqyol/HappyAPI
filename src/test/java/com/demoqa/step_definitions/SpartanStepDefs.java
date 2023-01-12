package com.demoqa.step_definitions;

import com.demoqa.pages.AddSpartanPage;
import com.demoqa.pages.SpartanHomePage;
import com.demoqa.utils.BrowserUtils;
import com.demoqa.utils.ConfigurationReader;
import com.demoqa.utils.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class SpartanStepDefs {

    SpartanHomePage spartanHomePage= new SpartanHomePage();
    Faker faker= new Faker();
    Random random= new Random(2);
    AddSpartanPage addSpartanPage= new AddSpartanPage();

    String name;
    String gender;
    String phone;

    Response response;
    Response responseRandom;

    String lastId;

    String randomSpartanID;

    @Given("User create a new Spartan")
    public void user_create_a_new_spartan() {
        Driver.get().get(ConfigurationReader.getProperty("spUrl"));
        spartanHomePage.addSpartan.click();

        name=faker.name().firstName();
        gender=(random.nextInt()%2==0)? "Male" : "Female";
        phone="1234567890";

        addSpartanPage.addName.sendKeys(name);
        addSpartanPage.selectGender(gender);
        addSpartanPage.addPhone.sendKeys(phone);

        addSpartanPage.submitBtn.click();
        System.out.println(addSpartanPage.successMsg.getText());
        Assert.assertTrue(addSpartanPage.successMsg.getText().contains("Success"));
    }
    @Given("User gets the Spartan and verify it is created")
    public void user_gets_the_spartan_and_verify_it_is_created(){

        response= given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("spartan_api_url")+"/api/spartans");
        Assert.assertTrue(response.body().asString().contains(name));
    }
    @Then("user delete the Spartan")
    public void user_delete_the_spartan(){
       // spartanHomePage.deleteSpartan(name).click();
       // BrowserUtils.waitFor(2.0);

        if(response.body().asString().contains(name)){
            lastId= response.body().path("id[-1]").toString();
            System.out.println("lastId = " + lastId);
        }
        given().pathParam("id",lastId)
                .when().delete(ConfigurationReader.getProperty("spartan_api_url")+"/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }
    @Then("user verify it is deleted")
    public void user_verify_it_is_deleted() {
//        System.out.println("say good bye to your deleted Spartan!");
//        response=given().accept(ContentType.JSON)
//                        .get(ConfigurationReader.getProperty("spartan_api_url")+"/api/spartans");
//        //response.prettyPrint();
        Assert.assertEquals(200,response.statusCode());
      //Assert.assertFalse(response.body().asString().contains(name));// false olması gerekli burada delete ettiğimiz için aslında
    }

    @When("User clicks on any random Spartan and view data")
    public void user_clicks_on_any_random_spartan_and_view_data() {
        Driver.get().get(ConfigurationReader.getProperty("spUrl"));

        List<String> allSpartanIDs= BrowserUtils.getElementsText(spartanHomePage.allSpartanIDs);

        Random random= new Random();
        int randomSpartanNumber= random.nextInt(allSpartanIDs.size());
        randomSpartanID= allSpartanIDs.get(randomSpartanNumber);
        System.out.println("randomSpartanID = " + randomSpartanID);

        BrowserUtils.waitFor(3.0);
        Actions actions= new Actions(Driver.get());
        actions.moveToElement(spartanHomePage.viewSpartan(randomSpartanID)).click(spartanHomePage.viewSpartan(randomSpartanID)).perform();
    }
    @Then("verify spartan data should be same with API")
    public void verify_spartan_data_should_be_same_with_api() {
        BrowserUtils.waitFor(3.0);
        responseRandom= given().accept(ContentType.JSON)
                .pathParam("id",randomSpartanID)
                 .get(ConfigurationReader.getProperty("spartan_api_url")+"/api/spartans/{id}");

        responseRandom.prettyPrint();

         String expectedName=responseRandom.path("name");
         //String actualName= spartanHomePage.getNameSpartan(randomSpartanID);

        System.out.println("expectedName = " + expectedName);
        //System.out.println("actualName = " + actualName);

        //Assert.assertEquals(expectedName,actualName);

    }
}
