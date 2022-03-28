package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import plinth.PlinthInitializer;
import utils.BrowserHelper;
import utils.ConfigHelper;

public class SearchPage extends PlinthInitializer {
    @FindBy(id = "search_form_input_homepage")
    private WebElement txtSearch;


    WebDriver browser;

    public SearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void openUrl() {
        browser.get(configHelper.getPropValues("DDG_URL"));
    }

    public void searchForPhrase(String phrase) {
        BrowserHelper.sendKeys(txtSearch, phrase + Keys.ENTER);
    }
}
