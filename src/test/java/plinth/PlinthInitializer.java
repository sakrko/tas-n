package plinth;

import org.openqa.selenium.WebDriver;

public class PlinthInitializer {
    private static final ThreadLocal<WebDriver> threadLocalBrowser = new ThreadLocal<>();

    public static void setDriver(WebDriver browser) {
        threadLocalBrowser.set(browser);
    }

    public static WebDriver getDriver() {
        return threadLocalBrowser.get();
    }
}
