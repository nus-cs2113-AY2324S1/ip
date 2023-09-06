package Command;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

import java.util.ArrayList;

public class TaskManager {

    private final ArrayList<Task> taskList = new ArrayList<>();

    public void listTasks() {
        System.out.println("Here's your tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + "." + taskList.get(i));
        }
    }

    public void markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(index).markAsDone();
            System.out.println("    " + taskList.get(index));
        }
        else {
            printInvalidTaskMessage(index);
        }
    }

    public void unmarkTaskAsDone(int index) {
        if (isValidIndex(index)) {
            System.out.println("Nice! I've unmarked:");
            taskList.get(index).markAsUndone();
            System.out.println("    " + taskList.get(index));
        }
        else {
            printInvalidTaskMessage(index);
        }
    }

    public void addTodo(String description) {
        if (description.isEmpty()) {
            displayInvalidFormatMessage("todo");
            return;
        }

        Todo todo = new Todo(description);
        taskList.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + todo);
        displayTaskCount();
    }

    public void addDeadline(String description, String time) {
        if (description.isEmpty() || time.isEmpty()) {
            displayInvalidFormatMessage("deadline");
            return;
        }

        Deadline deadline = new Deadline(description, time);
        taskList.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + deadline);
        displayTaskCount();
    }

    public void addEvent(String description, String startTime, String endTime) {
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            displayInvalidFormatMessage("event");
            return;
        }

        String timeRange = startTime + " to " + endTime;
        Event event = new Event(description, timeRange);
        taskList.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + event);
        displayTaskCount();
    }

    void displayInvalidFormatMessage(String taskType) {
        switch(taskType) {
        case "todo":
            System.out.println("Invalid format. Description cannot be empty for todo.");
            break;
        case "deadline":
            System.out.println("Invalid format. Use: deadline <description> /by <time>");
            break;
        case "event":
            System.out.println("Invalid format. Use: event <description> /from <start_time> /to <end_time>");
            break;
        default:
            System.out.println("Invalid task format.");
            break;
        }
    }

    private void displayTaskCount() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskList.size();
    }

    private void printInvalidTaskMessage(int index) {
        System.out.println("Invalid task number " + (index + 1) + ". Try Again!");
    }
}

