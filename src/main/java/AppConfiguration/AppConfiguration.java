package AppConfiguration;

import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AppConfiguration {
    private String filename;
    private String fileContent;
    private JSONArray animals;
    private List<HashMap<String, String>> animalsInfo = new ArrayList<HashMap<String, String>>();

    public AppConfiguration(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        this.filename = filename;
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(reader);
            parseAnimals((JSONObject)obj);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void parseAnimals(JSONObject json) {
        animals = (JSONArray) json.get("animals");
        for (Object animal : animals) {
            parseAnimalObject((JSONObject) animal);
        }
    }

    private void parseAnimalObject(JSONObject animal) {
        HashMap<String, String> keyValues = new HashMap<String, String>();
        for (Object key : animal.keySet()) {
            String keyString = (String)key;
            keyValues.put(keyString, animal.get(keyString).toString());
        }
        animalsInfo.add(keyValues);
    }

    public String getFilename() {
        return filename;
    }

    public String getFileContent() {
        return fileContent;
    }

    public List<HashMap<String, String>> getAnimals() {
        return animalsInfo;
    }
}
