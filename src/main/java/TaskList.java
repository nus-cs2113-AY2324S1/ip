import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import dukey.DukeyException;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks (add/delete)
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @param words The description of the Todo task.
     * @param tasks The list of tasks.
     */
    public static void addTodo(String words, ArrayList<Task> tasks) {
 /*       String[] words = line.split(" ");
        String description = "";
        try {
            for (int i = 1; i < words.length; i++) {
                description += words[i] + " ";
            } */
            tasks.add(new Todo(words));
            tasks.get(tasks.size() - 1).printNewTask();
     /*   } catch(IndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println(DukeyException.todoDescriptionError());
            Ui.printLine();
        } */
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param line  The user input containing the index of the task.
     * @param tasks The list of tasks.
     */
    public static void deleteTask(String line, ArrayList<Task> tasks) {
        try {
            String[] words = line.split(" ");
            int index  = Integer.parseInt(words[1]) - 1;
            Task delete = tasks.get(index);
            tasks.get(index).printDeleteTask();
            tasks.remove(index);
        }
        catch(IndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println(DukeyException.todoDescriptionError());
            Ui.printLine();
        }
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     * @param description The description of the Event task.
     * @param tasks       The list of tasks.
     */
    public static void addEvent(String from, String to, String description, ArrayList<Task> tasks) {
            tasks.add(new Event(from, to, description));
            tasks.get(tasks.size() - 1).printNewTask();

    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the task.
     * @param tasks       The list of tasks.
     */
    public static void addDeadline(String description, String by, ArrayList<Task> tasks) {
           tasks.add(new Deadline(description, by));
            tasks.get(tasks.size() - 1).printNewTask();
    }

    /**
     * Unmarks a task in the task list.
     *
     * @param line  The user input containing the index of the task.
     * @param tasks The list of tasks.
     */
    public static void unmarkTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).unmarkTask();
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param line  The user input containing the index of the task.
     * @param tasks The list of tasks.
     */
    public static void markTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).setDone();
        Ui.printLine();
        System.out.println("Nice! I've marked this task as done:\n\t  " + tasks.get(taskNum));
        Ui.printLine();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        Ui.printLine();
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        Ui.printLine();
    }
}
