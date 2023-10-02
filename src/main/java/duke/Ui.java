package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface of the bot and controls the interaction with the user.
 */
public class Ui {

    private final String HORIZONTAL_LINE = "--------------------------------------------";
    private Scanner in;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Introduces the bot and greets the user with the current list of tasks.
     * @param tasks TaskList object containing the list of tasks.
     */
    public void introduceBot(TaskList tasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Lexi";

        System.out.println(logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name);

        if (tasks.getTasks().size() > 0) {
            printList(tasks);
        } else {
            System.out.println("Currently, you have no tasks in your list.");
        }

        System.out.println("How can I help you buddy?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints farewell message to the user.
     */
    public void farewellBot() {
        System.out.println("Have a wonderful day! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a numbered list of tasks.
     * @param tasks TaskList object containing the list of tasks.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println((i+1) + "." + tasks.getTasks().get(i));
        }
    }

    /**
     * Notifies user after a task has been added.
     * @param task Task object that has been added.
     * @param tasks TaskList object containing the updated list of tasks.
     */
    public void printTaskAddedMessage(Task task, ArrayList<Task> tasks) {
        System.out.println("Ok, I have added the following task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Notifies user after a task has been removed.
     * @param task Task object that has been removed.
     * @param tasks TaskList object containing the updated list of tasks.
     */
    public void printTaskRemovedMessage(Task task, TaskList tasks) {
        System.out.println("Alright, I have removed the following task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }

    /**
     * Reads the user input.
     * @return String containing the provided input.
     */
    public String readCommand() {
        String input = in.nextLine().trim();
        return input;
    }

    /**
     * Prints a horizontal line used for command separations in the dialogue.
     */
    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Notifies the user about the successful marking or unmarking of a task.
     * @param done Boolean value indicating whether the task is done.
     * @param tasks TaskList object containing the list of tasks.
     * @param index Index of the task to be marked as done.
     */
    public void printMark(boolean done, TaskList tasks, int index) {
        if (done) {
            System.out.println("Great! I have marked this task as done:");
        } else {
            System.out.println("Alright, I have marked this task as not done:");
        }
        System.out.println(tasks.getTasks().get(index - 1));
    }

    /**
     * Prints the list of tasks that match the provided keyword.
     * If no tasks match, user will be notified as well.
     * @param matchingTasks
     */
    public void printKeywordSearchResult(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            System.out.println("There are no matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i+1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Notifies user about deadline formatting issue and provides the expected input format.
     * @see Deadline
     */
    public void printInvalidDeadlineMessage() {
        System.out.println("Oops, I don't understand that! Please provide a valid deadline in the format: " +
                "deadline <description> /by <due date>");
    }

    /**
     * Notifies user about empty deadline input and provides the expected input format.
     * @see Deadline
     */
    public void printEmptyDeadlineMessage() {
        System.out.println("I am sorry, the deadline cannot be empty! Please provide a valid deadline in the " +
                "format: deadline <description> /by <due date>");
    }

    /**
     * Notifies user about invalid event input and provides the correct input formatting.
     * @see Event
     */
    public void printInvalidEventMessage() {
        System.out.println("Oops, I don't understand that! Please provide a valid event in the format: event " +
                "<description> /from <start date> /to <end date>");
    }

    /**
     * Notifies user about empty event input and provides the correct input formatting.
     * @see Event
     */
    public void printEmptyEventMessage() {
        System.out.println("I am sorry, the event cannot be empty! Please provide a valid event in the format: " +
                "event <description> /from <start date> /to <end date>");
    }

    /**
     * Notifies user about invalid task id input.
     */
    public void printInvalidTaskIdMessage() {
        System.out.println("This task id does not exist, please provide a valid task number!");
    }

    /**
     * Notifies user about empty todo input.
     */
    public void printEmptyTodoMessage() {
        System.out.println("I am sorry, the todo cannot be empty!");
    }

    /**
     * Notifies user about invalid data and time input and provides the correct input formatting.
     */
    public void printInvalidDateTimeMessage() {
        System.out.println("Oops, I don't understand that! Please provide valid date and time in a readable format " +
                "like: YYYY-MM-DD HH:MM");
    }
}
