package com.gpt.dumpgpt.shared;

public final class ProgramConstants {
    public static final String BOT_NAME = "DumbGPT";
    public static final String SEPARATOR = "____________________________________________________________";
    public static final int INVALID_TASK_NUM = -1;

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static int parsePositiveNumber(String number) {
        int taskNumber = INVALID_TASK_NUM;

        try {
            taskNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return taskNumber;
        }

        if (taskNumber <= 0) {
            return INVALID_TASK_NUM;
        }
        return taskNumber;
    }

    public static void printWrapped(String printOut) {
        printSeparator();
        System.out.println(printOut);
        printSeparator();
    }

    public static void printWrapped(String[] printOut) {
        printSeparator();
        for (String line : printOut) {
            System.out.println(line);
        }
        printSeparator();
    }

    public static void greet() {
        printWrapped(new String[]{
                String.format("Hello I'm %s", ProgramConstants.BOT_NAME),
                "What can I do for you?"
        });
    }

    private ProgramConstants() {
    }
}
