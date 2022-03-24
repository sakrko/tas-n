package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigHelper;

public class SearchPage {
    @FindBy(id = "search_form_input_homepage")
    private WebElement txtSearch;


    WebDriver browser;

    public SearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void openUrl() {
        browser.get(ConfigHelper.getPropValues("DDG_URL"));
    }

    public void searchForPhrase(String phrase) {
        txtSearch.sendKeys(phrase + Keys.ENTER);
    }
}
