package elgin.task;

import elgin.exception.DukeException;

import java.util.HashMap;

import static elgin.utils.FormatPrinter.formatPrint;
import static elgin.utils.Parser.parseArguments;
import static elgin.utils.Parser.parseTaskIndex;

public class TaskManager {
    private Task[] tasks;
    private int totalTasks;

    public TaskManager() {
        this.tasks = new Task[100];
        totalTasks = 0;
    }

    public void listTasks() {
        String[] allTasksDescription = new String[totalTasks + 1];
        allTasksDescription[0] = "Here are the tasks in your list:";

        for (int i = 0; i < totalTasks; i++) {
            allTasksDescription[i + 1] = (i + 1) + "." + tasks[i];
        }
        formatPrint(allTasksDescription);
    }

    public void addTodo(String command, String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description of " + command + " cannot be empty");
        }
        Todo task = new Todo(arguments);
        incrementAndPrintNewTask(task);
    }

    public void addDeadline(String command, String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description of " + command + " cannot be empty");
        }
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        String by = parsedArgs.get("by");
        Deadline task = new Deadline(description, by);
        incrementAndPrintNewTask(task);
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
        incrementAndPrintNewTask(task);
    }

    public void setTaskIsDone(String arguments, boolean isDone) throws DukeException {
        int idx = parseTaskIndex(arguments);
        idx--;
        if (idx < 0 || idx > totalTasks - 1) {
            throw new DukeException("Please enter a valid task number.");
        }
        tasks[idx].setIsDone(isDone);
        String doneMsg = isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        String[] messages = new String[]{
                doneMsg,
                "\t" + tasks[idx]
        };
        formatPrint(messages);
    }

    public String getNumberOfTasks() {
        String taskWord = totalTasks > 1 ? " tasks" : " task";
        return "Now you have " + totalTasks + taskWord + " in the list.";
    }

    public void incrementAndPrintNewTask(Task t) {
        tasks[totalTasks] = t;
        totalTasks++;
        String[] messages = new String[]{
                "Got it. I've added this task:",
                "\t" + t,
                getNumberOfTasks()
        };
        formatPrint(messages);
    }

}
