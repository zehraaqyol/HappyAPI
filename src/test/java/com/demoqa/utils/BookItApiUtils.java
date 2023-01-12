package com.demoqa.utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class BookItApiUtils {

    public static  String generateToken(String email, String password){
        Response response= given().queryParam("email",email)
                .and().queryParam("password",password)
                .when().get(ConfigurationReader.getProperty("qa3api.url"+"/sign"));

        String token=response.path("accessToken");
        String fullToken= "Bearer"+token;

        return fullToken;
    }
    public String [] getMyInfo(String email,String password){
        String [] myInfo= new String[3];

        for(int i=0; i<myInfo.length; i++ ){
            if(i==0){
                String url= ConfigurationReader.getProperty("qa3api.url"+"/teams/my");
                Response response= given().accept(ContentType.JSON)
                        .and().header("Authorization",generateToken(email, password))
                        .when().get(url);
                JsonPath jsonPath= response.jsonPath();
                myInfo[i]=jsonPath.getString("name");
            }
            if(i==1){
                String url= ConfigurationReader.getProperty("qa3api.url"+"batches/my");
                Response response= given().accept(ContentType.JSON)
                        .header("Authorization",generateToken(email,password))
                        .get(url);
                JsonPath jsonPath= response.jsonPath();
                myInfo[i]= jsonPath.getString("njumber");
            }
            if(i==2){
                String url= ConfigurationReader.getProperty("qa3api.url"+"campuses/my");
                Response response= given().accept(ContentType.JSON)
                        .header("Authorization",generateToken(email,password))
                        .get(url);
                JsonPath jsonPath= response.jsonPath();
                myInfo[i]=jsonPath.getString("location");
            }
        }
        return myInfo;
    }

}

