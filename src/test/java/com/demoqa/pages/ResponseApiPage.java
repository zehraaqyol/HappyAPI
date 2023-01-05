package com.demoqa.pages;

import com.demoqa.utils.ConfigurationReader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseApiPage {

    public Map<String,String> getRequestBody(){
        String requestUserName= ConfigurationReader.getProperty("userName");
        String requestPassword= ConfigurationReader.getProperty("passWord");
        Map<String,String> requestBody= new LinkedHashMap<>();
        requestBody.put("userName",requestUserName);
        requestBody.put("password",requestPassword);
        return requestBody;
    }

}
