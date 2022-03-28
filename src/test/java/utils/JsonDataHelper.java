package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class JsonDataHelper {
    private static final Logger logger = LogManager.getLogger(JsonDataHelper.class);
    private List<Map<String, String>> tcDataList = null;
    private Map<String, String> tcData = null;

    private JsonDataHelper() {
    }

    private static class InstanceHolder {
        private static final JsonDataHelper instance = new JsonDataHelper();
    }

    public static JsonDataHelper getInstance() {
        logger.info("JsonDataHelper Initiating an Object");
        return JsonDataHelper.InstanceHolder.instance;
    }

    private void getJsonObject() {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "/src/test/resources/data/TestData_" + PropHelper.getEnvironment() + ".json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert reader != null;
        tcDataList = gson.fromJson(reader, List.class);
    }

    public void loadTestDataScenario(String scenario) {
        getJsonObject();
        for (Map<String, String> obj : tcDataList) {
            if (obj.get("id").equalsIgnoreCase(scenario)) {
                tcData = obj;
                logger.info("Data from Json: " + obj);
            }
        }
    }

    public Map<String, String> getDataMap() {
        return tcData;
    }
}
