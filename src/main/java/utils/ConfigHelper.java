package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigHelper {
    INSTANCE;

    private Properties getProp() {
        Properties prop = null;
        String fileName = "config_" + PropHelper.getEnvironment() + ".properties";
        try {
            InputStream fis = ConfigHelper.class.getClassLoader().getResourceAsStream(fileName);
            prop = new Properties();
            prop.load(fis);
            assert fis != null;
            fis.close();
        } catch (IOException fnfe) {
            fnfe.printStackTrace();
        }
        return prop;
    }

    public String getConfigProp(String key) {
        return getProp().getProperty(key);
    }
}
