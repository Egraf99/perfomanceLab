package task3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashMap<Long, String> mapIdStatus = getMapIdStatus(getJsonFromFile(args[1]));

        JSONObject json = getJsonFromFile(args[0]);
        JSONArray tests = (JSONArray) json.get("tests");
        replaceValue(tests, mapIdStatus);
        writeIntoFile(json, "report.json");
    }

    private static JSONObject getJsonFromFile(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static HashMap<Long, String> getMapIdStatus(JSONObject jsonObject) {
        HashMap<Long, String> mapIdStatus = new HashMap<>();
        JSONArray values = (JSONArray) jsonObject.get("values");
        for (Object o : values) {
            Long id = (Long) ((JSONObject) o).get("id");
            String value = (String) ((JSONObject) o).get("value");
            mapIdStatus.put(id, value);
        }
        return mapIdStatus;
    }

    private static void replaceValue(JSONArray json, HashMap<Long, String> map) {
        for (Object test : json) {
            JSONObject jtest = (JSONObject) test;
            // если есть поле value, то заполняем его по id, используя хеш таблицу
            String value = (String) jtest.get("value");
            if (value != null) {
                jtest.put("value", map.get(jtest.get("id")));
            }

            // если есть поле values, то сорекурсивно вызываем функцию для JSONArray внутри values
            JSONArray values = (JSONArray) jtest.get("values");
            if (values != null) {
                replaceValue(values, map);
            }
        }
    }

    private static void writeIntoFile(JSONObject json, String path) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}