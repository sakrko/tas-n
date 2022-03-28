package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import plinth.PlinthInitializer;
import utils.BrowserHelper;
import utils.ConfigHelper;

public class ElementsPage extends PlinthInitializer {
    //    Locators
    @FindBy(xpath = "//*[text()='Full Name']//following::input[1]")
    WebElement txtFullName;

    @FindBy(xpath = "//*[text()='Email']//following::input[1]")
    WebElement txtEmail;

    @FindBy(xpath = "//*[text()='Current Address']//following::textarea[1]")
    WebElement txaCurrentAddress;

    @FindBy(xpath = "//*[text()='Permanent Address']//following::textarea[1]")
    WebElement txaPermanentAddress;

    @FindBy(xpath = "//*[text()='Submit']")
    WebElement btnSubmit;

    @FindBy(xpath = "//span[text()='Text Box']")
    WebElement lnkTextBox;

    @FindBy(xpath = "//*[@class='border col-md-12 col-sm-12']")
    WebElement lblResultData;


    //    Initializers
    WebDriver browser;
    private static final Logger logger = LogManager.getLogger(ConfigHelper.class);


    public ElementsPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    //    Interaction Methods
    public void fillTextField(String fieldName) {
        WebElement element = null;
        String value = null;
        switch (fieldName) {
            case "fullName":
                element = txtFullName;
                value = jsonDataHelper.getDataMap().get("fullName");
                break;
            case "email":
                element = txtEmail;
                value = jsonDataHelper.getDataMap().get("email");
                break;
            case "currentAddress":
                element = txaCurrentAddress;
                value = jsonDataHelper.getDataMap().get("currentAddress");
                break;
            case "permanentAddress":
                element = txaPermanentAddress;
                value = jsonDataHelper.getDataMap().get("permanentAddress");
                break;
            default:
                Assert.fail("No such field is found");
                break;
        }
        BrowserHelper.sendKeys(element, value);
    }


    public void clickTextBox() {
        BrowserHelper.clickElement(lnkTextBox);
    }

    public void submit() {
        BrowserHelper.clickElement(btnSubmit);
    }

    public String getSubmittedData() {
        return BrowserHelper.getNonInputDataWithAttribute(lblResultData);
    }
}
