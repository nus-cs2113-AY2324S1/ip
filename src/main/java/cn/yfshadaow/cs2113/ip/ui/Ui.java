package cn.yfshadaow.cs2113.ip.ui;

import cn.yfshadaow.cs2113.ip.XiaoAiBot;

import java.util.Scanner;

/**
 * The UI class used for receiving user's input and printing output
 */
public class Ui {
    private static final String BOT_NAME = "XiaoAi";
    private static final String SPLIT = "____________________________________________";
    private static final String GREET_MESSAGE = "Welcome back, master!\n" +
            BOT_NAME + " here. What can I do for you?";

    private static final String QUIT_MESSAGE = "See you next time, master!";
    private final XiaoAiBot bot;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Instantiates a new Ui instance.
     *
     * @param bot the bot
     */
    public Ui(XiaoAiBot bot) {
        this.bot = bot;
    }

    /**
     * Send message to user.
     *
     * @param message the message to be sent
     */
    public void sendMessage(String message) {
        sendMessageWithoutSplit(message);
        sendSplit();
    }

    /**
     * Greets the user.
     */
    public void greet() {
        sendMessage(GREET_MESSAGE);
    }

    /**
     * Farewell to the user.
     */
    public void farewell() {
        sendMessage(QUIT_MESSAGE);
    }

    /**
     * Send message without split.
     *
     * @param message the message to be sent
     */
    public void sendMessageWithoutSplit(String message) {
        System.out.println(message);
    }

    /**
     * Send split line to user
     */
    public void sendSplit() {
        System.out.println(SPLIT);
    }

    /**
     * Read line from scanner
     *
     * @return the string read from the scanner
     */
    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            sendMessage(String.format("Error reading line: %s", e.getMessage()));
            return null;
        }
    }
}
