package lemon.ui;

import lemon.task.Task;
import lemon.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static lemon.common.Messages.*;

public class UI {
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void displayDivider() {
        out.println(LEMON_DIVIDER);
    }

    public void displayMessage(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LINE_SEPARATOR));
        }
    }

    public void displayWelcome() {
        displayMessage(LEMON_LOGO, MESSAGE_INTRODUCTION, MESSAGE_WELCOME, LEMON_START_AND_END);
    }

    public void displayExit() {
        displayMessage(MESSAGE_EXIT, LEMON_START_AND_END);
    }

    public String getInput() {
        String input = in.nextLine();
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }
        return input;
    }

    public void displayEmptyList() {
        displayMessage(MESSAGE_EMPTY_LIST);
    }

    public void displayList(ArrayList<Task> tasks) {
        int taskSerialNo = 1;
        for (Task task : tasks) {
            System.out.println(taskSerialNo + ". " + task.toString());
            taskSerialNo++;
        }
    }

    public void displayTaskList(ArrayList<Task> tasks) {
        displayMessage(MESSAGE_DISPLAY_LIST);
        displayList(tasks);
    }

    public void displayAddedTask(Task task, TaskList tasks) {
        displayMessage(MESSAGE_ADDED_TASK);
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " fruitful tasks in your basket!");
    }

    public void displayDeletedTask(Task task, TaskList tasks) {
        displayMessage(MESSAGE_DELETED_TASK);
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " fruitful tasks in your basket!");
    }

    public void displayMarkedTask(Task task) {
        displayMessage(MESSAGE_MARKED_TASK);
        System.out.println("\t" + task.toString());
    }

    public void displayUnmarkedTask(Task task) {
        displayMessage(MESSAGE_UNMARKED_TASK);
        System.out.println("\t" + task.toString());
    }

    public void displayFoundList(ArrayList<Task> tasks) {
        displayMessage(MESSAGE_FOUND_TASK);
        displayList(tasks);
    }

    public void displayUnfoundTask() {
        displayMessage(MESSAGE_UNFOUND_TASK);
    }

    public void displayHelp() {
        displayMessage(
                HELP_MESSAGE,
                HELP_LIST_MESSAGE, COMMAND_LIST_FORMAT,
                HELP_TODO_MESSAGE, COMMAND_TODO_FORMAT,
                HELP_DEADLINE_MESSAGE, COMMAND_DEADLINE_FORMAT,
                HELP_EVENT_MESSAGE, COMMAND_EVENT_FORMAT,
                HELP_MARK_MESSAGE, COMMAND_MARK_FORMAT,
                HELP_UNMARK_MESSAGE, COMMAND_UNMARK_FORMAT,
                HELP_FIND_MESSAGE, COMMAND_FIND_FORMAT,
                HELP_DELETE_MESSAGE, COMMAND_DELETE_FORMAT,
                HELP_BYE_MESSAGE, COMMAND_BYE_FORMAT
        );
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}
