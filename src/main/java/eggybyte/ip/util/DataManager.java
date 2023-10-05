package eggybyte.ip.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import eggybyte.ip.data.DateTime;
import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.*;
import eggybyte.ip.util.Logger.LogLevel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataManager {
    private static String absolutePath;
    private static String home = System.getProperty("user.home");

    public static void setRelativePath(String relativePath) throws IOException {
        String dataFolderPath = home + "\\AppData\\LocalLow\\EggyByte\\iP\\";
        // Logger.customPrint(dataFolderPath);
        Files.createDirectories(Paths.get(dataFolderPath));

        absolutePath = dataFolderPath + relativePath;
        // Logger.customPrint(absolutePath);
    }

    public static String readData() throws IOException {
        File file = new File(absolutePath);

        // No existing file
        if (file.createNewFile()) {
            return "";
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String result = "", line;
        while ((line = reader.readLine()) != null) {
            result += line + "\n";
        }
        reader.close();
        return result.trim();
    }

    public static void saveData(String content) throws Exception {
        File file = new File(absolutePath);
        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
        Logger.showLog("Your data has been saved at the path:\n  " + absolutePath, LogLevel.IMPORTANT, false);
    }

    public static <CustomType> CustomType convertFromJson(String json) {
        Type type = new TypeToken<CustomType>() {
        }.getType();

        Gson gson = new Gson();

        return gson.fromJson(json, type);
    }

    public static ArrayList<Task> convertFromJsonToTaskList(String json) {
        Type type = new TypeToken<ArrayList<Task>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .registerTypeAdapter(DateTime.class, new DateTimeAdapter())
                .create();

        return gson.fromJson(json, type);
    }

    public static String convertToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    private static class TaskAdapter implements JsonDeserializer<Task> {
        @Override
        public Task deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            String type = jsonObject.get("type").getAsString();
            switch (type) {
                case "T":
                    return context.deserialize(jsonObject, Todo.class);
                case "D":
                    return context.deserialize(jsonObject, Deadline.class);
                case "E":
                    return context.deserialize(jsonObject, Event.class);
                default:
                    throw new JsonParseException("Unknown task type: " + type);
            }
        }
    }

    private static class DateTimeAdapter implements JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            JsonObject jsonObject = json.getAsJsonObject();

            String rawData = jsonObject.get("rawData").getAsString();
            try {
                return new DateTime(rawData);
            } catch (TipsException e) {
                Logger.showLog(e.toString(), LogLevel.DEBUG, false);
            }
            return null;
        }
    }
}
