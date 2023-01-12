package com.demoqa.step_definitions;

import com.demoqa.utils.ConfigurationReader;
import com.demoqa.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setUpRequest(){
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // RestAssured.baseURI = ConfigurationReader.getProperty("spUrl");
    }
    @After
    public void tearDown(Scenario scenario){
//        //only takes a screenshot if the scenario fails
//        if(scenario.isFailed()){
//            //taking a screenshot
//            final byte[] screenshot= ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot,"image/png");
//        }
        Driver.closeDriver();
    }
}
