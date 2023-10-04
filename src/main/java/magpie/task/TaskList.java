package magpie.task;

import java.util.ArrayList;
import magpie.files.Storage;

/**
 * Represents a <b>TaskList</b> for initializing a <code>tasks</code> list to track Task objects.<br>
 * Contains methods to manage Tasks such as <code>list</code>, <code>add</code>, <code>delete</code>,
 * <code>find</code>, and <code>mark</code>.
 */
public class TaskList {

    private static ArrayList<Task> tasks;
    private static int taskCount;

    /**
     * Constructs <code>taskCount</code> and to initialize <code>ArrayList</code> for <code>tasks</code>.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Displays list of current <code>Task</code> objects in <code>tasks</code>
     */
    public static void listTasks() {

        if (taskCount == 0) {
            System.out.println("You have no tasks in your list :)");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {

                Task item = tasks.get(i);
                System.out.println(i + 1 + ". " + item.toString());
            }

            System.out.println("____________________________________________________________\n");
        }
    }

    /**
     * Displays valid indexes (if any) for the user when <code>IndexOutOfBoundsException</code> is caught.
     */
    public static void displayIndexError() {
        if (taskCount == 0) {
            System.out.print("Wow! Looks like you have no tasks to manage. Add one first!\n");
        } else {
            System.out.print("Your input was not a valid index! Please choose from 1 to " + taskCount + "\n");
        }
    }

    /**
     * Initializes a new <code>Todo</code> object with given parameters.
     * Pass new <code>Todo</code> object to <code>addTask</code> method.
     *
     * @param isMark true or false value indicating mark or unmark.
     * @param description description of task.
     */
    public static void addTodo(boolean isMark, String description) {

        Task newTask = new Todo(description);
        newTask.setDone(isMark);
        addTask(newTask);

    }

    /**
     * Initializes a new <code>Deadline</code> object with given parameters.
     * Pass new <code>Deadline</code> object to <code>addTask</code> method.
     *
     * @param isMark true or false value indicating mark or unmark.
     * @param description Description of task.
     * @param by By deadline of task.
     */
    public static void addDeadline(boolean isMark, String description, String by) {
        Task newTask = new Deadline(description, by.trim());
        newTask.setDone(isMark);
        addTask(newTask);

    }

    /**
     * Initializes a new <code>Event</code> object with given parameters.
     * Pass new <code>Event</code> object to <code>addTask</code> method.
     *
     * @param isMark true or false value indicating mark or unmark.
     * @param description Description of event.
     * @param from Starting time of event.
     * @param to Ending time of event.
     */
    public static void addEvent(boolean isMark, String description, String from, String to) {
        Task newTask = new Event(description, from.trim(), to.trim());
        newTask.setDone(isMark);
        addTask(newTask);

    }

    /**
     * Adds a <code>Task</code> object to <code>tasks</code> list and prints success message.
     * Calls <code>Storage</code> method to append task details to file.
     *
     * @param t Task object to be added to list.
     */
    public static void addTask(Task t) {

        tasks.add(t);
        taskCount++;

        System.out.println("____________________________________________________________\n");
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        System.out.println("____________________________________________________________\n");
        Storage.appendTaskToFile(t.getTextToWrite() + System.lineSeparator());
    }


    /**
     * Deletes a <code>Task</code> object from <code>tasks</code> list and prints success message.
     * Calls <code>Storage</code> method to delete task details from file.
     *
     * @param index Index of task to be deleted.
     */
    public static void deleteTask(int index) {

        try {
            Task t = tasks.get(index);
            tasks.remove(index);
            taskCount--;
            Storage.deleteTaskFromFile(t.getTextToWrite());
            System.out.println("____________________________________________________________\n");
            System.out.println("Noted. I've removed this task: ");
            System.out.println("  " + t);
            System.out.println("Now you have " + taskCount + " task(s) in the list.");
            System.out.println("____________________________________________________________\n");
        } catch(IndexOutOfBoundsException e) {
            displayIndexError();
        }

    }

    /**
     * Finds a <code>Task</code> object from <code>tasks</code> list and prints found task details (if any).
     *
     * @param keyword Keyword to search for.
     */
    public static void findTask(String keyword) {

        System.out.println("____________________________________________________________\n");
        System.out.println("Finding tasks with keyword: " + keyword + ".....\n");
        int count = 0;

        for (int i = 0; i < taskCount; i++ ) {

            Task t = tasks.get(i);
            String taskDescription = t.description;

            if (taskDescription.contains(keyword)) {
                System.out.println((count+1) + ". " + t.toString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No task(s) were found with keyword: " + keyword + "\n");
        } else {
            System.out.println(count + " task(s) were found with keyword: " + keyword + "\n");
        }
        System.out.println("____________________________________________________________\n");

    }

    /**
     * Marks or Unmarks a <code>Task</code> object in <code>tasks</code> list based on boolean value given.
     * Prints success message if there were no errors.
     *
     * @param index Index of task.
     * @param isDone true to indicate a Mark operation, false to indicate Unmark operation.
     */
    public static void markTask(int index, boolean isDone) {

        try {
            Task item = tasks.get(index);
            String oldLine = item.getTextToWrite();
            item.setDone(isDone);
            System.out.println("____________________________________________________________\n");
            if (isDone) {
                System.out.print("Nice! I've marked this task as done:\n");
            } else {
                System.out.print("OK, I've marked this task as not done yet:\n");

            }
            System.out.println("  " + item);
            System.out.println("____________________________________________________________\n");
            Storage.updateTaskInFile(oldLine, item.getTextToWrite());
        } catch (IndexOutOfBoundsException e) {
            displayIndexError();
        } catch (NullPointerException e) {
            displayIndexError();

        }

    }

}
