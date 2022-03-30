package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import plinth.PlinthInitializer;
import utils.BrowserHelper;

public class BookStorePage extends PlinthInitializer {
    //    Locators
    @FindBy(xpath = "//input[@id='searchBox']")
    WebElement txtSearchBox;

    @FindBy(xpath = "(//*[@class='rt-tr-group'])[1]//child::a")
    WebElement lnkFirstResultBookName;


    //    Initializers
    WebDriver browser;

    public BookStorePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    //    Interaction Methods
    public void searchParticularBook(String book) {
        BrowserHelper.sendKeys(txtSearchBox, book);
    }

    public String getFirstBookNameFromResultList() {
        String resultBook;
        resultBook = BrowserHelper.getNonInputDataWithAttribute(lnkFirstResultBookName);
        return resultBook;
    }

    public int getResultListCount() {
        int resultCount;
        resultCount = browser.findElements(By.xpath("(//*[@class='rt-tr-group'])[1]//child::a")).size();
        return resultCount;
    }

}
