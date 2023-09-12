package duke;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;
import static duke.Duke.taskList;


/**
 *
 * This class fully manages printing Ui output
 */
public class DukeUi {

    private final String LINE = "____________________________________________________________";

    public void printLine() {
        System.out.println(LINE);
    }

    public void printWelcomeMessage() {
        printLine();
        System.out.println("Hi! I'm Joshua");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printTaskList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public void printAddedTask(Task task, TaskList taskList) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
        printLine();
    }

    public void printMarkedTask(int taskIndex, TaskList taskList) {
        printLine();
        List<Task> tasks = taskList.getTasks();
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            System.out.println("Ok! I've marked this task as done:");
            System.out.println(" " + task.toString());
        } else {
            System.out.println("Invalid task index.");
        }
        printLine();
    }

    public void printUnmarkedTask(int taskIndex, TaskList taskList) {
        printLine();
        List<Task> tasks = taskList.getTasks();
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + task.toString());
        } else {
            System.out.println("Invalid task index.");
        }
        printLine();
    }

    public void printToDoTaskException() {
        printLine();
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        printLine();
    }

    public void printDeadlineException() {
        printLine();
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        printLine();
    }

    public void printEventException() {
        printLine();
        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        printLine();
    }
}
