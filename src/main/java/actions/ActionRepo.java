package main.java.actions;


import main.java.utils.ElementFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import main.java.Base.BaseConfigurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/*
Base Action repository where defined all the core actions perform on selected element
 */
public class ActionRepo {

    public void click(String elementType ,String element){
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement(elementType,element).click();
    }

    public void type(String elementType,String element,String value){
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement(elementType,element).sendKeys(value);
    }

    public void isElementPresent(String elementType,String elementValue,boolean visibility,long waitTime){

        Duration waitDuration = Duration.ofSeconds(waitTime);
        Duration pollingDuration = Duration.ofMillis(200);
        ElementFetch elementFetch = new ElementFetch();
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(BaseConfigurations.driver)
                .withTimeout(waitDuration)
                .pollingEvery(pollingDuration);
        try {
            fluentWait.until(ExpectedConditions.visibilityOf(elementFetch.getWebElement(elementType,elementValue)));
            Assert.assertTrue(visibility,"Element not present");
        }catch (Exception e){
                Assert.assertTrue(!visibility,"Element not present");
        }

    }

    public void waitTillElementToLoad(String elementType,String elementValue,long waitTime){

        Duration waitDuration = Duration.ofSeconds(waitTime);
        Duration pollingDuration = Duration.ofMillis(200);
        ElementFetch elementFetch = new ElementFetch();
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(BaseConfigurations.driver)
                .withTimeout(waitDuration)
                .pollingEvery(pollingDuration);
        fluentWait.until(ExpectedConditions.visibilityOf(elementFetch.getWebElement(elementType,elementValue)));

    }

    public String getElementText(String elementType,String elementValue,long waitTime){

        Duration waitDuration = Duration.ofSeconds(waitTime);
        Duration pollingDuration = Duration.ofMillis(200);
        ElementFetch elementFetch = new ElementFetch();
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(BaseConfigurations.driver)
                .withTimeout(waitDuration)
                .pollingEvery(pollingDuration);
        WebElement element = fluentWait.until(ExpectedConditions.visibilityOf(elementFetch.getWebElement(elementType,elementValue)));
        return  element.getText();
    }


}
