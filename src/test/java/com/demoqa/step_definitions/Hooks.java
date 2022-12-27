package com.demoqa.step_definitions;

import com.demoqa.utils.ConfigurationReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    @Before
    public void setUpRequest(){
        RestAssured.baseURI = ConfigurationReader.getProperty("apiUrl");
    }
}
