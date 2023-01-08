package com.demoqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddSpartanPage extends BasePage{

    @FindBy(id = "name")
    public WebElement addName;

    @FindBy(id = "genderSelect")
    public WebElement addGender;

    @FindBy(id = "phone")
    public WebElement addPhone;

    @FindBy(id = "submit_btn")
    public WebElement submitBtn;

   public void selectGender(String str){
       Select select= new Select(addGender);
       select.selectByValue(str);
   }
    @FindBy(xpath = "//div[contains(text(),'Successfully Added new Data!')]")
    public WebElement successMsg;
}
