package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {


    WebDriver browser;

    public ResultPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    @FindBy(id = "search_form_input")
    WebElement txtSearchInput;

    public String getSearchInput() {
        return txtSearchInput.getAttribute("value");
    }
}
