package cn.yfshadaow.cs2113.ip;

import java.util.Scanner;

public class XiaoAiBot {
    private final String BOT_NAME = "XiaoAi";
    private final String SPLIT_LINE = "____________________________________________";
    private final String GREET_MESSAGE = "Welcome back, master!\n" +
            BOT_NAME + " here. What can I do for you?";

    private final String QUIT_MESSAGE = "See you next time, master!";

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    private CommandHandler commandHandler = new CommandHandler(this);
    private Scanner scanner = new Scanner(System.in);

    public void setShouldQuit(boolean shouldQuit) {
        this.shouldQuit = shouldQuit;
    }

    private boolean shouldQuit = false;

    private void initialize() {
        // for further modification
    }

    public void sendMessages(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SPLIT_LINE);
    }

    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        initialize();
        sendMessages(GREET_MESSAGE);

        while (!shouldQuit) {
            String command = readLine();
            commandHandler.handleCommand(command);
        }

        sendMessages(QUIT_MESSAGE);
    }
}
