package parsing.com;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GSONParser {

    public void runProcess() {
        try {
            JsonArray jsonArray = getJsonElements(new File("src/main/resources/JSON input.json"));
            List<JsonElement> resultList = getSpecificJson(jsonArray);
            writeJsonIntoFile(resultList);
            readJsonFromFile(new File("src/main/resources/JSON output"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JsonArray getJsonElements(File input) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader(input));
        return jsonElement.getAsJsonArray();
    }

    List<JsonElement> getSpecificJson(JsonArray jsonArray) {
        List<JsonElement> resultList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement element = jsonArray.get(i);
            if (element.toString().contains("USD") || element.toString().contains("EUR")
                    || element.toString().contains("RUB")) {
                resultList.add(element);
            }
        }
        return resultList;
    }

    private void writeJsonIntoFile(List<JsonElement> resultList) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("src/main/resources/JSON output"));
        Gson gson = new GsonBuilder().create();
        gson.toJson(resultList, fileWriter);
        fileWriter.flush();
    }

    private void readJsonFromFile(File input) throws FileNotFoundException {
        JsonArray jsonArray = getJsonElements(input);
        for (JsonElement jsonElement : jsonArray) {
            System.out.println(jsonElement.toString());
        }
    }
}
