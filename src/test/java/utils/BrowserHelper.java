package utils;


import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import plinth.PlinthInitializer;

import java.time.Duration;

public class BrowserHelper extends PlinthInitializer {
    private static WebDriverWait webDriverWait() {
        return new WebDriverWait(getBrowser(), Duration.ofSeconds(Integer.parseInt(configHelper.getConfigProp("ImplicitWait"))));
    }

    private static JavascriptExecutor jsExecutor() {
        return (JavascriptExecutor) getBrowser();
    }

    public static void sendKeys(WebElement element, String keys) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keys);
    }

    public static String getInputDataWithAttribute(WebElement element, String attribute) {
        return webDriverWait().until(ExpectedConditions.elementToBeClickable(element)).getAttribute(attribute);
    }

    public static void openBrowser(String url) {
        WebDriver browser = getBrowser();
        browser.get(url);
        browser.manage().window().maximize();
    }

    public static void clickElement(WebElement element) {
        try {
            webDriverWait().until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            jsExecutor().executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
    }

    public static String getNonInputDataWithAttribute(WebElement element) {
        return webDriverWait().until(ExpectedConditions.elementToBeClickable(element)).getText();
    }
}
