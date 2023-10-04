package cn.yfshadaow.cs2113.ip;

import cn.yfshadaow.cs2113.ip.command.Command;
import cn.yfshadaow.cs2113.ip.command.CommandHandler;
import cn.yfshadaow.cs2113.ip.task.Deadline;
import cn.yfshadaow.cs2113.ip.task.Event;
import cn.yfshadaow.cs2113.ip.task.Task;
import cn.yfshadaow.cs2113.ip.task.Todo;
import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class XiaoAiBot {
    private static final RuntimeTypeAdapterFactory<Task> taskAdapterFactory = RuntimeTypeAdapterFactory.of(Task.class, "type")
            .registerSubtype(Deadline.class, "Deadline")
            .registerSubtype(Event.class, "Event")
            .registerSubtype(Todo.class, "Todo");
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(taskAdapterFactory)
            .setPrettyPrinting()
            .create();

    private static final String BOT_NAME = "XiaoAi";
    private static final String SPLIT = "____________________________________________";
    private static final String GREET_MESSAGE = "Welcome back, master!\n" +
            BOT_NAME + " here. What can I do for you?";

    private static final String QUIT_MESSAGE = "See you next time, master!";

    private static final String DATA_DIRECTORY_PATH = "./data/";

    private static final String TASK_DATA_FILE_PATH = DATA_DIRECTORY_PATH + "taskData.json";

    private final File taskDataFile = new File(TASK_DATA_FILE_PATH);


    private final List<Task> tasks = new LinkedList<>();

    public List<Task> getTasks() {
        return tasks;
    }


    @SuppressWarnings("unused")
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    private final CommandHandler commandHandler = new CommandHandler(this);
    private final Scanner scanner = new Scanner(System.in);

    public void setShouldQuit(boolean shouldQuit) {
        this.shouldQuit = shouldQuit;
    }

    private boolean shouldQuit = false;

    private void initialize() throws IOException{
        Files.createDirectories(Paths.get(DATA_DIRECTORY_PATH));
        taskDataFile.createNewFile();
        try {
            loadData();
            sendMessage("Successfully loaded data from data file");
        } catch (IllegalStateException e) {
            sendMessage(String.format("Failed to load data from data file: %s", e.getMessage()));
        } catch (JsonParseException e) {
            sendMessage(String.format("Failed to load data becauseJSON parsing failed: %s", e.getMessage()));
        }
    }

    public void saveData() throws IOException {
        JsonObject dataObject = new JsonObject();
        JsonArray tasksArray = new JsonArray();
        for (Task t: tasks) {
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

    public void loadData() throws IOException, IllegalStateException, JsonParseException {
        tasks.clear();
        String dataString = FileUtils.readFileToString(taskDataFile, StandardCharsets.UTF_8);
        JsonObject dataObject = JsonParser.parseString(dataString).getAsJsonObject();
        JsonArray tasksArray = dataObject.getAsJsonArray("tasks");
        for (JsonElement e : tasksArray.asList()) {
            tasks.add(gson.fromJson(e, Task.class));
        }
    }
    public void sendMessage(String message) {
        sendMessageWithoutSplit(message);
        sendSplit();
    }

    public void sendMessageWithoutSplit(String message) {
        System.out.println(message);
    }

    public void sendSplit() {
        System.out.println(SPLIT);
    }

    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            sendMessage(String.format("Error reading line: %s", e.getMessage()));
            return null;
        }
    }

    public void start() {
        try {
            initialize();
        } catch (Exception e) {
            sendMessage(String.format("Bot initialization failed: %s", e.getMessage()));
            return;
        }
        sendMessage(GREET_MESSAGE);

        while (!shouldQuit) {
            String commandString = readLine();
            Command cmd;
            try {
                cmd = Command.parseCommand(commandString);
            } catch (IllegalArgumentException e) {
                sendMessage(String.format("Error parsing command: %s", e.getMessage()));
                continue;
            }
            commandHandler.handleCommand(cmd);
        }

        sendMessage(QUIT_MESSAGE);
    }
}
