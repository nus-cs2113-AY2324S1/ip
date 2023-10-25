import Exceptions.DukeyErrorMessages;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks (add/delete/mark)
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
            tasks.add(new Todo(words));
            tasks.get(tasks.size() - 1).printNewTask();
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
            int index  = Integer.parseInt(words[1].trim()) - 1;
            Task element = tasks.get(index);
            tasks.remove(index);
            element.printDeleteTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeyErrorMessages.deleteEmptyInputError();
        } catch (IndexOutOfBoundsException e) {
            DukeyErrorMessages.deleteInvalidTypeError();
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
        try {
            String[] words = line.split(" ");
            int taskNum = Integer.parseInt(words[1]) - 1;
            tasks.get(taskNum).setDone();
            Ui.printLine();
            System.out.println("Nice! I've marked this task as done:\n\t  " + tasks.get(taskNum));
            Ui.printLine();
        } catch (NumberFormatException e) {
            DukeyErrorMessages.markInvalidError();
        }
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

    /**
     * Searches for tasks containing a specified keyword and prints the results.
     *
     * @param line   The user input containing the keyword search command.
     * @param tasks  The list of tasks to be searched.
     */
    protected static void findKeyword(String line, ArrayList<Task> tasks) {
            ArrayList<Task> searchResults = new ArrayList<>();
            String keyword = line.substring(4).trim();
            if (keyword.isEmpty()) {
                DukeyErrorMessages.findEmptyInputError();
                return;
            }
            int count = 0;
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    searchResults.add(task);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("There are no matching tasks in your list");
            } else {
                Ui.outputHeader();
            }
            for (Task task : searchResults) {
                System.out.println(task);
            }
            Ui.printLine();
    }
}
