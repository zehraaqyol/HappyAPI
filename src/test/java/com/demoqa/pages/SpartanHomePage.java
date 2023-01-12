package com.demoqa.pages;

import com.demoqa.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpartanHomePage extends BasePage {

    @FindBy(xpath = "//tbody//tr")
    public List<WebElement> allSpartans;

    @FindBy(xpath = "//tbody//tr//td[1]")
    public List<WebElement> allSpartanIDs;

    @FindBy(partialLinkText = "Add Spartan")
    public WebElement addSpartan;

    @FindBy(xpath = "//tbody//td[7]")
    public List<WebElement> deleteBtns;

    //  //tbody/tr//td[.='Mahmut']/..//td[7]/a
    public WebElement deleteSpartan(String name){
        return Driver.get().findElement(By.xpath("//tbody/tr//td[.='"+name+"']/..//td[7]/a"));
    }
    public WebElement viewSpartan(String id){
        return Driver.get().findElement(By.xpath("//tbody/tr//td[.='"+id+"']/..//td[5]/a"));
    }
    public String getNameSpartan(String id){
        return Driver.get().findElement(By.xpath("//tbody//tr//td[.='"+id+"']/..//td[2]")).getText();
    }


}
