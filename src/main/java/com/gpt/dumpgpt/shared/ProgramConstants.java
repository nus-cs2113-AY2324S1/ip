package com.gpt.dumpgpt.shared;

import com.gpt.dumpgpt.task.TaskManager;

public final class ProgramConstants {
    public static final String BOT_NAME = "DumbGPT";
    public static final String SEPARATOR = "____________________________________________________________";
    public static final int INVALID_POS_NUM = -1;

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Parses a string into number if is valid
     * @param number string to be parsed
     * @return a positive integer if parsed string was valid
     *         otherwise, will return {@link #INVALID_POS_NUM}
     */
    public static int parsePositiveNumber(String number) {
        int taskNumber = INVALID_POS_NUM;

        try {
            taskNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return taskNumber;
        }

        if (taskNumber <= 0) {
            return INVALID_POS_NUM;
        }
        return taskNumber;
    }

    /**
     * Print program output wrapped with {@link #SEPARATOR}
     * @param printOut string to be printed
     */
    public static void printWrapped(String printOut) {
        printSeparator();
        System.out.println(printOut);
        printSeparator();
    }

    /**
     * Print output wrapped with {@link #SEPARATOR},
     * each string in array will be separated by newline
     * @param printOut string to be printed
     */
    public static void printWrapped(String[] printOut) {
        printSeparator();
        for (String line : printOut) {
            System.out.println(line);
        }
        printSeparator();
    }

    /**
     * Prints program greeting
     */
    public static void greet() {
        TaskManager taskManager = new TaskManager();
        printWrapped(new String[]{
                String.format("Hello I'm %s", ProgramConstants.BOT_NAME),
                "What can I do for you?",
                String.format("You currently have %d tasks!", taskManager.getTasks().size())
        });
    }

    private ProgramConstants() {
    }
}
