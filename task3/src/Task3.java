import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) {

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String reportFilePath = args[2];

        try {
            String testsJsonString = new String(Files.readAllBytes(Paths.get(testsFilePath)));
            String valuesJsonString = new String(Files.readAllBytes(Paths.get(valuesFilePath)));

            JSONObject testsJson = new JSONObject(testsJsonString);
            JSONObject valuesJson = new JSONObject(valuesJsonString);

            Map<Integer, String> valuesMap = new HashMap<>();
            JSONArray valuesArray = valuesJson.getJSONArray("values");

            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject valueObject = valuesArray.getJSONObject(i);
                valuesMap.put(valueObject.getInt("id"), valueObject.getString("value"));
            }

            fillValues(testsJson.getJSONArray("tests"), valuesMap);

            Files.write(Paths.get(reportFilePath), testsJson.toString(4).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Успешно: Отчет создан по пути " + reportFilePath);

        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
        }
    }

    private static void fillValues(JSONArray testsArray, Map<Integer, String> valuesMap) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject testObject = testsArray.getJSONObject(i);
            int id = testObject.getInt("id");
            if (valuesMap.containsKey(id)) {
                testObject.put("value", valuesMap.get(id));
            }
            if (testObject.has("values")) {
                fillValues(testObject.getJSONArray("values"), valuesMap);
            }
        }
    }
}

