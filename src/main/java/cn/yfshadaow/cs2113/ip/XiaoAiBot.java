package cn.yfshadaow.cs2113.ip;

import cn.yfshadaow.cs2113.ip.command.Command;
import cn.yfshadaow.cs2113.ip.command.CommandHandler;
import cn.yfshadaow.cs2113.ip.storage.Storage;
import cn.yfshadaow.cs2113.ip.ui.Ui;
import cn.yfshadaow.cs2113.ip.utils.Parser;
import cn.yfshadaow.cs2113.ip.utils.TaskList;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Scanner;

public class XiaoAiBot {

    private final Storage storage = new Storage();

    @SuppressWarnings("unused")
    public Storage getStorage() {
        return storage;
    }

    private final Ui ui = new Ui(this);

    @SuppressWarnings("unused")
    public Ui getUi() {
        return ui;
    }

    private final TaskList taskList = new TaskList();

    @SuppressWarnings("unused")
    public TaskList getTaskList() {
        return taskList;
    }


    @SuppressWarnings("unused")
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    private final CommandHandler commandHandler = new CommandHandler(this);

    public void setShouldQuit(boolean shouldQuit) {
        this.shouldQuit = shouldQuit;
    }

    private boolean shouldQuit = false;

    private void initialize() throws IOException{
        storage.initialize();
        try {
            taskList.tasks.addAll(storage.loadData().tasks);
            ui.sendMessage("Successfully loaded data from data file");
        } catch (JsonParseException e) {
            ui.sendMessage(String.format("Failed to load data becauseJSON parsing failed: %s", e.getMessage()));
        } catch (Exception e) {
            ui.sendMessage(String.format("Failed to load data from data file: %s", e.getMessage()));
        }
    }


    public void start() {
        try {
            initialize();
        } catch (Exception e) {
            ui.sendMessage(String.format("Bot initialization failed: %s", e.getMessage()));
            return;
        }
        ui.greet();

        while (!shouldQuit) {
            String commandString = ui.readLine();
            Command cmd;
            try {
                cmd = Parser.parseCommand(commandString);
            } catch (IllegalArgumentException e) {
                ui.sendMessage(String.format("Error parsing command: %s", e.getMessage()));
                continue;
            }
            commandHandler.handleCommand(cmd);
        }

        ui.farewell();
    }
}
