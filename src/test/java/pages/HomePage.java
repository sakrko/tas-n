package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BrowserHelper;

public class HomePage {
    //    Locators
    @FindBy(xpath = "//*[text()='Book Store Application']//ancestor::div[@class='card mt-4 top-card']")
    WebElement lblBookStoreApplication;

    @FindBy(xpath = "//*[text()='Elements']//ancestor::div[@class='card mt-4 top-card']")
    WebElement lblElements;


    //    Initializers
    WebDriver browser;


    public HomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    //    Interaction Methods
    public void clickBlock(String block) {
        WebElement element = null;
        switch (block) {
            case "bookStoreApplication":
                element = lblBookStoreApplication;
                break;
            case "elements":
                element = lblElements;
                break;
            default:
                Assert.fail("No such block is found");
                break;
        }
        BrowserHelper.clickElement(element);
    }
}
