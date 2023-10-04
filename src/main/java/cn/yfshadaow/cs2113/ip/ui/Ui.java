package cn.yfshadaow.cs2113.ip.ui;

import cn.yfshadaow.cs2113.ip.XiaoAiBot;

import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "XiaoAi";
    private static final String SPLIT = "____________________________________________";
    private static final String GREET_MESSAGE = "Welcome back, master!\n" +
            BOT_NAME + " here. What can I do for you?";

    private static final String QUIT_MESSAGE = "See you next time, master!";
    private final XiaoAiBot bot;
    private final Scanner scanner = new Scanner(System.in);
    public Ui(XiaoAiBot bot) {
        this.bot = bot;
    }
    public void sendMessage(String message) {
        sendMessageWithoutSplit(message);
        sendSplit();
    }

    public void greet() {
        sendMessage(GREET_MESSAGE);
    }

    public void farewell() {
        sendMessage(QUIT_MESSAGE);
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
}
