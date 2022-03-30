package plinth;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ConfigHelper;
import utils.JsonDataHelper;

import java.util.concurrent.ConcurrentMap;

public class PlinthInitializer {
    private static final ThreadLocal<WebDriver> threadLocalBrowser = new ThreadLocal<>();

    public static synchronized void setBrowser(WebDriver browser) {
        threadLocalBrowser.set(browser);
    }

    public static synchronized WebDriver getBrowser() {
        return threadLocalBrowser.get();
    }

    public static ConfigHelper configHelper = ConfigHelper.INSTANCE;

    public static JsonDataHelper jsonDataHelper = JsonDataHelper.INSTANCE;

    private static final ThreadLocal<ConcurrentMap<String, String>> tlCM = new ThreadLocal<>();

    public static synchronized void setCM(ConcurrentMap<String, String> cm) {
        tlCM.set(cm);
    }

    public static synchronized ConcurrentMap<String, String> getCM() {
        return tlCM.get();
    }

    private static final ThreadLocal<Scenario> tlS = new ThreadLocal<>();

    public static synchronized void setS(Scenario cm) {
        tlS.set(cm);
    }

    public static synchronized Scenario getS() {
        return tlS.get();
    }
}
