package doli.GUI;

import java.util.Scanner;


public class Ui {
    private static final Scanner IN = new Scanner(System.in);
    private static final String LOGO = " ____       _\n"
            + "|  _  \\    | | [_]\n"
            + "| | | |____| |  _\n"
            + "| |_| | [] | | | |\n"
            + "|____/|____|__||_|\n";
    private static final String BULLET_POINT = "\n\t\u2022 ";
    private static final String INTRO = "I can help you create an agenda to manage your tasks.\n"
            + "Simply use one of the below listed commands to proceed:"
            + BULLET_POINT + "todo: adds a task with a DESCRIPTION"
            + BULLET_POINT + "deadline /: adds a task with DESCRIPTION and /DEADLINE"
            + BULLET_POINT + "event / /: adds a task with DESCRIPTION, /STARTDATE and /ENDDATE"
            + BULLET_POINT + "mark: marks the task with the provided INDEX as done"
            + BULLET_POINT + "unmark: resets the task with the provided INDEX as not done"
            + BULLET_POINT + "count: returns the total number of tasks in the agenda"
            + BULLET_POINT + "delete: deletes the task with the provided INDEX"
            + BULLET_POINT + "clear: deletes all tasks within the agenda"
            + BULLET_POINT + "list: lists all current entries in the agenda"
            + BULLET_POINT + "bye: exits the program\n";
    private static final String USERNAME = "User:";
    private static final String CHATBOT = "Doli:";
    private static final String WELCOME_MESSAGE = String.format("Hello, my name is\n%s\n"
            + INTRO + "How can I help you?", LOGO);
    private static void print(String str) {
        System.out.println(str);
    }
    public Ui() {
    }
    public void welcomeUser() {
        print(WELCOME_MESSAGE);
    }

    public void printHLine() {
        print("________________________________");
    }

    public void showError(String error) {
        print(error);
    }
    public void showLoadingError() {
        print("Loading Error");
    }

    public String getInput() {
        String input = IN.nextLine();
        return input;
    }
}