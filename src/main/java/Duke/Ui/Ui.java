package Duke.Ui;

import Duke.Task.Task;
import Duke.Task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LINE_DASHES = "____________________________________________________________";
    private final String START_MESSAGE = "Hello! I'm Chatty\n" + "What can I do for you?";
    private final String END_MESSAGE = "Bye. Hope to see you again soon!";
    private final String LIST_TASK_FORMAT = "%d.%s\n";
    private final String ADD_TASK_MESSAGE = "\tGot it. I've added this task:\n"
            + "\t%s\n"
            + "\tNow you have %d task(s) in the list.";
    private final Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Iterate and prints out all the tasks in taskList onto the console.
     *
     * @param notification Additonal message to be printed before printing all the tasks.
     * @param taskList TaskList to be iterated.
     */
    public void printAllTasks(String notification, TaskList taskList) {

        StringBuilder message = new StringBuilder(notification);
        for (int i = 1; i < taskList.getNumTask() + 1; i++) {
            message.append(String.format((LIST_TASK_FORMAT), i, taskList.getTask(i)));
        }
        printMessage(message.toString());
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
     * @param task     Task to be added.
     * @param taskList TaskList that is added to.
     */
    public void printTaskAdded(Task task, TaskList taskList) {
        printMessage(String.format(ADD_TASK_MESSAGE,task,taskList.getNumTask()));
    }
}
