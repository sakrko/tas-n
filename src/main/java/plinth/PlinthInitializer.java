package plinth;

import org.openqa.selenium.WebDriver;

public class PlinthInitializer {
    private static final ThreadLocal<WebDriver> threadLocalBrowser = new ThreadLocal<>();

    public void setDriver(WebDriver browser) {
        threadLocalBrowser.set(browser);
    }

    public WebDriver getDriver() {
        return threadLocalBrowser.get();
    }
}
