package cn.yfshadaow.cs2113.ip;

import cn.yfshadaow.cs2113.ip.command.Command;
import cn.yfshadaow.cs2113.ip.command.CommandHandler;
import cn.yfshadaow.cs2113.ip.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XiaoAiBot {
    private static final String BOT_NAME = "XiaoAi";
    private static final String SPLIT = "____________________________________________";
    private static final String GREET_MESSAGE = "Welcome back, master!\n" +
            BOT_NAME + " here. What can I do for you?";

    private static final String QUIT_MESSAGE = "See you next time, master!";


    private final List<Task> tasks = new ArrayList<>();

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

    private void initialize() {
        // for further modification
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
        initialize();
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
