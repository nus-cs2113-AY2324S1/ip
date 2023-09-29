package Duke.Ui;

import Duke.Task.Task;
import Duke.Task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LINE_DASHES = "____________________________________________________________";
    private final String START_MESSAGE = "Hello! I'm Chatty\n" + "What can I do for you?";
    private final Scanner in;
    private final String END_MESSAGE = "Bye. Hope to see you again soon!";
    public Ui(){
        in = new Scanner(System.in);
    }

    /**
     * Iterate and prints out all the tasks in taskList onto the console.
     *
     * @param taskList TaskList to be iterated.
     */
    public void printAllTasks(TaskList taskList) {
        for (int i = 1; i < taskList.getNumTask() + 1; i++) {
            System.out.println(taskList.getTask(i));
        }
    }

    public void printWelcomeMessage() {
        printMessage(START_MESSAGE);
    }

    public void printByeMessage() {
        printMessage(END_MESSAGE);
    }

    public void printLine() {
        System.out.println(LINE_DASHES);
    }
    public void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints the ui for task added.
     *
     * @param task Task to be added.
     * @param taskList TaskList that is added to.
     */
    public void printTaskAdded(Task task, TaskList taskList) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("\tNow you have " + taskList.getNumTask() + " tasks in the list.");
        printLine();
    }
}
