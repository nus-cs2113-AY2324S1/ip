package elgin.task;

import elgin.exception.DukeException;

import java.util.ArrayList;
import java.util.HashMap;

import static elgin.utils.FormatPrinter.formatPrint;
import static elgin.utils.Parser.parseArguments;
import static elgin.utils.Parser.parseTaskIndex;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void listTasks() {
        int totalTasks = getTaskSize();
        String[] allTasksDescription = new String[totalTasks + 1];
        allTasksDescription[0] = "Here are the tasks in your list:";

        for (int i = 0; i < totalTasks; i++) {
            allTasksDescription[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        formatPrint(allTasksDescription);
    }

    public void addTodo(String command, String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description of " + command + " cannot be empty");
        }
        Todo task = new Todo(arguments);
        addToTasks(task);
    }

    public void addDeadline(String command, String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description of " + command + " cannot be empty");
        }
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        String by = parsedArgs.get("by");
        Deadline task = new Deadline(description, by);
        addToTasks(task);
    }

    public void addEvent(String command, String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description of " + command + " cannot be empty");
        }
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        String from = parsedArgs.get("from");
        String to = parsedArgs.get("to");
        Event task = new Event(description, from, to);
        addToTasks(task);
    }

    public void setTaskIsDone(String arguments, boolean isDone) throws DukeException {
        int totalTasks = getTaskSize();
        int idx = parseTaskIndex(arguments);
        idx--;
        if (idx < 0 || idx > totalTasks - 1) {
            throw new DukeException("Please enter a valid task number.");
        }
        tasks.get(idx).setIsDone(isDone);
        String doneMsg = isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        String[] messages = new String[]{
                doneMsg,
                "\t" + tasks.get(idx)
        };
        formatPrint(messages);
    }

    public String getNumberOfTasks() {
        int totalTasks = getTaskSize();
        String taskWord = totalTasks > 1 ? " tasks" : " task";
        return "Now you have " + totalTasks + taskWord + " in the list.";
    }

    public void addToTasks(Task t) {
        tasks.add(t);
        String[] messages = new String[]{
                "Got it. I've added this task:",
                "\t" + t,
                getNumberOfTasks()
        };
        formatPrint(messages);
    }

    public int getTaskSize() {
        return tasks.size();
    }

}
