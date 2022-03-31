package utils;

public class PropHelper {
    private static String browserName;
    private static String environment;

    public static void setEnvironment(String env) {
        environment = env;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static void setBrowserName(String bName) {
        browserName = bName;
    }

    public static String getBrowserName() {
        return browserName;
    }
}
