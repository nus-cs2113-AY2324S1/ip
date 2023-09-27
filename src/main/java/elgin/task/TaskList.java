package elgin.task;

import elgin.exception.DukeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static elgin.parser.Parser.*;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String[] listTasks() {
        int totalTasks = getTaskSize();
        String[] allTasksDescription = new String[totalTasks + 1];
        allTasksDescription[0] = "Here are the tasks in your list:";

        for (int i = 0; i < totalTasks; i++) {
            allTasksDescription[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return allTasksDescription;
    }

    public String[] addTodo(String arguments) throws DukeException {
        Todo task = new Todo(arguments);
        return addToTasks(task);
    }

    public String[] addDeadline(String command, String arguments) throws DukeException {
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        LocalDateTime by = parseDateTime(parsedArgs.get("by"));
        Deadline task = new Deadline(description, by);
        return addToTasks(task);
    }

    public String[] addEvent(String command, String arguments) throws DukeException {
        HashMap<String, String> parsedArgs = parseArguments(command, arguments);
        String description = parsedArgs.get("description");
        LocalDateTime from = parseDateTime(parsedArgs.get("from"));
        LocalDateTime to = parseDateTime(parsedArgs.get("to"));
        isValidFromToDateTime(from, to);
        Event task = new Event(description, from, to);
        return addToTasks(task);
    }

    public String[] setTaskIsDone(int index, boolean isDone) throws DukeException {
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

    public String getNumberOfTasks() {
        int totalTasks = getTaskSize();
        String taskWord = totalTasks > 1 ? " tasks" : " task";
        return "Now you have " + totalTasks + taskWord + " in the list.";
    }

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

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
