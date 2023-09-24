package Ui;
import Soccat.Task;
import Storage.TaskList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ui {

    static final String WELCOME = "Hello! I'm soccat!\nWhat can I do for you?";
    static final String GOODBYE = "Thank you for using Soccat, hope to see you again soon!";
    static final String LINE = "____________________________________________________________";
    public final String IO_EXCEPTION_MESSAGE = "IO Exception occurred during file operation";
    public final String INDEX_OFB_EXCEPTION_MESSAGE = "Oops, task number is out of range!\n" +
            "Please use task index from 1 to ";
    public final String FILE_CREATION_ERROR = "Failed to create data file ";

    public void displayLine() {
        System.out.println(LINE);
    }

    public void displayWelcome() {
        displayLine();
        System.out.println(WELCOME);
        displayLine();
    }

    public void displayGoodbye() {
        displayLine();
        System.out.println(GOODBYE);
    }

    public void displayTask(Task task) {
        System.out.println("\t" + task);
    }

    public void displayAllTasks(TaskList tasks) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        for (int i = 0; i < taskArrayList.size(); i++) {
            // Add 1 to change array index to 1 based index
            int arrayIndex = i + 1;
            System.out.println(arrayIndex + ". " + taskArrayList.get(i));
        }
    }

    public void displaySelectedTasks(TaskList tasks, ArrayList<Integer> indexes) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        for (Integer index : indexes) {
            // Add 1 to change array index to 1 based index
            int arrayIndex = index + 1;
            System.out.println(arrayIndex + ". " + taskArrayList.get(index));
        }
    }

    public void displayAddedTask(Task task, TaskList tasks, String taskType) {
        System.out.println("Got it. I've added this " + taskType + ":");
        displayTask(task);
        displayTaskCount(tasks);
    }

    public void displayRemovedTask(Task task, TaskList tasks) {
        System.out.println("Noted, I have removed this task:");
        displayTask(task);
        displayTaskCount(tasks);
    }

    public void displayTaskCount(TaskList tasks) {
        int taskListLength = tasks.getTaskListLength();
        if (taskListLength == 0) {
            System.out.println("You have no tasks for now, you may take a break.");
            return;
        }
        System.out.println("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
    }

    public void displayMessage (String message) {
        System.out.println(message);
    }

    public void displayError (String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

}
