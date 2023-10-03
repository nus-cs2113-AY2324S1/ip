package elgin.task;

import elgin.exception.DukeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static elgin.parser.Parser.*;


public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for Tasklist.
     * This will be called if saved tasks file failed to load.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for Tasklist.
     *
     * @param tasks Tasks loaded from saved file.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists all tasks in the arraylist.
     *
     * @return Messages containing the string representation of all tasks.
     */
    public String[] listTasks() {
        int totalTasks = getTaskSize();
        String[] allTasksDescription = new String[totalTasks + 1];
        allTasksDescription[0] = "Here are the tasks in your list:";

        for (int i = 0; i < totalTasks; i++) {
            allTasksDescription[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return allTasksDescription;
    }

    /**
     * Loops through all the tasks and find the tasks
     * that contains the keyword specified by the user.
     *
     * @param keyword Keyword to be searched for.
     * @return Messages containing the list of matched tasks.
     */
    public String[] findTasks(String keyword) {
        int totalTasks = getTaskSize();
        String[] allMatchingTasks = new String[totalTasks + 1];

        int counter = 1;
        for (int i = 0; i < totalTasks; i++) {
            String taskDescription = tasks.get(i).getDescription().toLowerCase();
            if (taskDescription.contains(keyword)) {
                allMatchingTasks[counter] = (counter) + "." + tasks.get(i);
                counter++;
            }
        }
        if (counter != 1) {
            allMatchingTasks[0] = "Here are the matching tasks in your list:";
        } else {
            allMatchingTasks[0] = "Sorry no match found.";
        }
        return Arrays.copyOf(allMatchingTasks, counter);
    }

    /**
     * Adds the Todo task to the tasks arraylist.
     *
     * @param arguments Arguments supplied by the user containing description.
     * @return Messages to be printed after adding Todo.
     */
    public String[] addTodo(String arguments) {
        Todo task = new Todo(arguments);
        return addToTasks(task);
    }

    /**
     * Adds the Deadline task to the tasks arraylist.
     * Parses arguments as datetime.
     *
     * @param command Command supplied by the user.
     * @param arguments Arguments supplied by the user containing description and /by.
     * @return Messages to be printed after adding Deadline.
     * @throws DukeException If arguments are invalid.
     */
    public String[] addDeadline(String command, String arguments) throws DukeException {
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        LocalDateTime by = parseDateTime(parsedArgs.get("by"));
        Deadline task = new Deadline(description, by);
        return addToTasks(task);
    }

    /**
     * Adds the Event task to the tasks arraylist.
     * Parses arguments as datetime.
     *
     * @param command Command supplied by the user.
     * @param arguments Arguments supplied by the user containing description, /from, /to.
     * @return Messages to be printed after adding Event.
     * @throws DukeException If arguments are invalid.
     */
    public String[] addEvent(String command, String arguments) throws DukeException {
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        LocalDateTime from = parseDateTime(parsedArgs.get("from"));
        LocalDateTime to = parseDateTime(parsedArgs.get("to"));
        isValidFromToDateTime(from, to);
        Event task = new Event(description, from, to);
        return addToTasks(task);
    }

    /**
     * Set a task isDone status.
     *
     * @param index Index of the task.
     * @param isDone True if the task is completed, else False.
     * @return Messages to be printed after setting the isDone status of Task.
     */
    public String[] setTaskIsDone(int index, boolean isDone) {
        index--;
        tasks.get(index).setIsDone(isDone);
        String doneMsg = isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        String[] messages = new String[]{
                doneMsg,
                "\t" + tasks.get(index)
        };
        return messages;
    }

    /**
     * Returns message containing number of tasks
     *
     * @return Message
     */
    public String getNumberOfTasks() {
        int totalTasks = getTaskSize();
        String taskWord = totalTasks > 1 ? " tasks" : " task";
        return "Now you have " + totalTasks + taskWord + " in the list.";
    }

    /**
     * Adds a task to the Task Arraylist.
     *
     * @param t Task to be added
     * @return Messages to be printed after adding task.
     */
    public String[] addToTasks(Task t) {
        tasks.add(t);
        String[] messages = new String[]{
                "Got it. I've added this task:",
                "\t" + t,
                getNumberOfTasks()
        };
        return messages;
    }


    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Delete a task based on the index supplied by user.
     *
     * @param index The index of the task to be deleted.
     * @return Messages to be printed after deleted task.
     */
    public String[] deleteTask(int index) {
        index--;
        Task t = tasks.get(index);
        tasks.remove(index);
        String[] messages = new String[]{
                "Noted. I've removed this task:",
                "\t" + t,
                getNumberOfTasks()
        };
        return messages;
    }

    /**
     * Gets all tasks.
     *
     * @return ArrayList of all the tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
