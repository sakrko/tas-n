package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plinth.PlinthInitializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum JsonDataHelper {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger(JsonDataHelper.class);
    private List<Map<String, String>> testCases = null;


    private void loadTestData() {
        Gson gson = new Gson();
        JsonReader reader = null;
        String path = System.getProperty("user.dir") + "/src/test/resources/data/TestData_" + PropHelper.getEnvironment() + ".json";
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert reader != null;
        testCases = gson.fromJson(reader, List.class);
    }

    public ConcurrentMap<String, String> loadTestDataScenario(String scenario) {
        if (testCases == null) {
            loadTestData();
        } else {
            logger.info("Test Data is in memory");
        }
        Map<String, String> testData = null;
        for (Map<String, String> obj : testCases) {
            if (obj.get("id").equalsIgnoreCase(scenario)) {
                testData = obj;
                logger.info("Data from Json: " + obj);
            }
        }
        assert testData != null;
        return new ConcurrentHashMap<>(testData);
    }

    public ConcurrentMap<String, String> getDataMap() {
        return PlinthInitializer.getConMap();
    }
}
