package cn.yfshadaow.cs2113.ip.storage;

import cn.yfshadaow.cs2113.ip.XiaoAiBot;
import cn.yfshadaow.cs2113.ip.task.Deadline;
import cn.yfshadaow.cs2113.ip.task.Event;
import cn.yfshadaow.cs2113.ip.task.Task;
import cn.yfshadaow.cs2113.ip.task.Todo;
import cn.yfshadaow.cs2113.ip.utils.TaskList;
import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    public void initialize() throws IOException {
        Files.createDirectories(Paths.get(DATA_DIRECTORY_PATH));
        taskDataFile.createNewFile();
    }

    private static final String DATA_DIRECTORY_PATH = "./data/";

    private static final String TASK_DATA_FILE_PATH = DATA_DIRECTORY_PATH + "taskData.json";

    private final File taskDataFile = new File(TASK_DATA_FILE_PATH);

    private static final RuntimeTypeAdapterFactory<Task> taskAdapterFactory = RuntimeTypeAdapterFactory.of(Task.class, "type")
            .registerSubtype(Deadline.class, "Deadline")
            .registerSubtype(Event.class, "Event")
            .registerSubtype(Todo.class, "Todo");
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(taskAdapterFactory)
            .setPrettyPrinting()
            .create();

    public void saveData(TaskList list) throws IOException {
        JsonObject dataObject = new JsonObject();
        JsonArray tasksArray = new JsonArray();
        for (Task t: list.tasks) {
            if (t instanceof Todo) {
                tasksArray.add(gson.toJsonTree(t, Task.class));
            } else if (t instanceof Deadline) {
                tasksArray.add(gson.toJsonTree(t, Task.class));
            } else if (t instanceof Event) {
                tasksArray.add(gson.toJsonTree(t, Task.class));
            }
        }
        dataObject.add("tasks", tasksArray);
        FileUtils.write(taskDataFile, gson.toJson(dataObject), StandardCharsets.UTF_8);
    }

    public TaskList loadData() throws IOException, IllegalStateException, JsonParseException {
        TaskList list = new TaskList();
        String dataString = FileUtils.readFileToString(taskDataFile, StandardCharsets.UTF_8);
        JsonObject dataObject = JsonParser.parseString(dataString).getAsJsonObject();
        JsonArray tasksArray = dataObject.getAsJsonArray("tasks");
        for (JsonElement e : tasksArray.asList()) {
            list.tasks.add(gson.fromJson(e, Task.class));
        }
        return list;
    }
}
