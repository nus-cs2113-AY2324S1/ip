package com.gpt.dumpgpt.shared;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.command.Parser;
import com.gpt.dumpgpt.task.TaskManager;

import java.util.Scanner;

public class Ui {
    public static final String BOT_NAME = "DumbGPT";
    public static final String SEPARATOR = "____________________________________________________________";
    public static final String USER_PROMPT = "User: ";
    private final Scanner SCANNER = new Scanner(System.in);

    public String getInput() {
        System.out.print(USER_PROMPT);
        return SCANNER.nextLine();
    }

    /**
     * Print program output wrapped with {@link #SEPARATOR}
     *
     * @param printOut string to be printed
     */
    public void printWrapped(String printOut) {
        printSeparator();
        System.out.println(printOut);
        printSeparator();
    }

    /**
     * Print output wrapped with {@link #SEPARATOR},
     * each string in array will be separated by newline
     *
     * @param printOut string to be printed
     */
    public void printWrapped(String[] printOut) {
        printSeparator();
        for (String line : printOut) {
            System.out.println(line);
        }
        printSeparator();
    }

    /**
     * Prints program greeting
     */
    public void greet() {
        TaskManager taskManager = new TaskManager();
        printWrapped(new String[]{
                String.format("Hello I'm %s", BOT_NAME),
                "What can I do for you?",
                String.format("You currently have %d tasks!", taskManager.getTasks().size())
        });
    }

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

}
