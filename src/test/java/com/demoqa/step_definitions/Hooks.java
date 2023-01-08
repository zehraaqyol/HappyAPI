package com.demoqa.step_definitions;

import com.demoqa.utils.ConfigurationReader;
import com.demoqa.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setUpRequest(){
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // RestAssured.baseURI = ConfigurationReader.getProperty("spUrl");
    }
    @After
    public void tearDown(){
        Driver.closeDriver();
    }
}
