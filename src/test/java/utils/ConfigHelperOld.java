package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//public class ConfigHelper {
//    private static String result = "";
//    private static InputStream inputStream;
//    private static final Logger logger = LogManager.getLogger(ConfigHelper.class);
//
////    private ConfigHelper() {
////    }
////
////    private static class InstanceHolder {
////        private static final ConfigHelper instance = new ConfigHelper();
////    }
////
////    public static ConfigHelper getInstance() {
////        logger.info("ConfigHelper Initiating an Object");
////        return InstanceHolder.instance;
////    }
//
//    private static ConfigHelper configHelper;
//
//    private ConfigHelper() {
//    }
//
//    public static ConfigHelper getInstance() {
//        if (configHelper != null) {
//        }
//        synchronized (ConfigHelper.class) {
//            if (configHelper == null) {
//                configHelper = new ConfigHelper();
//            }
//        }
//        return configHelper;
//    }
//
//
//    public String getPropValues(String key) {
//        try {
//            Properties prop = new Properties();
//            String propFileName = "config_" + PropHelper.getEnvironment() + ".properties";
//
//            inputStream = ConfigHelper.class.getClassLoader().getResourceAsStream(propFileName);
//
//            if (inputStream != null) {
//                prop.load(inputStream);
//            } else {
//                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//            }
//
//
//            result = prop.getProperty(key);
//            logger.info(key + ": " + result + " is loaded");
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        } finally {
//            assert inputStream != null;
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//
//}

public enum ConfigHelperOld {
    INSTANCE;
    private static String result = "";
    private static InputStream inputStream;
    private static final Logger logger = LogManager.getLogger(ConfigHelperOld.class);

    public String getPropValues(String key) {
        try {
            Properties prop = new Properties();
            String propFileName = "config_" + PropHelper.getEnvironment() + ".properties";

            inputStream = ConfigHelperOld.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


            result = prop.getProperty(key);
            logger.info(key + ": " + result + " is loaded");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}