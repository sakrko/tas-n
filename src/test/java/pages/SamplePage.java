package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import plinth.PlinthInitializer;

public class SamplePage extends PlinthInitializer {
    WebDriver browser ;

    public SamplePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void opneUrl(String url) {
        browser.get(url);
    }
}
