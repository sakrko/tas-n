package plinth;

import org.openqa.selenium.WebDriver;
import utils.ConfigHelper;
import utils.JsonDataHelper;

public class PlinthInitializer {
    private static final ThreadLocal<WebDriver> threadLocalBrowser = new ThreadLocal<>();

    public static void setBrowser(WebDriver browser) {
        threadLocalBrowser.set(browser);
    }

    public static WebDriver getBrowser() {
        return threadLocalBrowser.get();
    }

    public static ConfigHelper configHelper = ConfigHelper.getInstance();

    public static JsonDataHelper jsonDataHelper = JsonDataHelper.getInstance();
}
