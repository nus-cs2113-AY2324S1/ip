package data;

import data.exception.IncompleteDescriptionException;
import data.task.Deadline;
import data.task.Event;
import data.task.Task;
import data.task.Todo;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    public static final String LINE = "------------------------------------------------------------";
    public static final String MESSAGE_UNMARK = "OK, I've marked this data.task as not done yet:";
    public static final String MESSAGE_MARK = "Nice! I've marked this data.task as done:";
    public static final String MESSAGE_EMPTY_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String MESSAGE_EMPTY_DEADLINE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String MESSAGE_EMPTY_EVENT = "☹ OOPS!!! The description of an event cannot be empty.";

    private ArrayList<Task> tasks;

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTodo(String todoDescription) throws IncompleteDescriptionException {
        String[] descriptions = todoDescription.split(" ");

        if (descriptions.length < 2) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_TODO);
        } else {
            String description = descriptions[1];
            Task todo = new Todo(description);
            tasks.add(todo);
            Ui.printTaskAddedMessage(todo);
        }
    }

    public void addDeadline(String deadlineDescription) {
        String[] descriptions = deadlineDescription.split("/by");
        String description = descriptions[0].trim();
        String date = descriptions[1].trim();

        Task deadline = new Deadline(description, date);
        tasks.add(deadline);
        Ui.printTaskAddedMessage(deadline);
    }

    public void addEvent(String eventDescription) {
        String[] descriptions = eventDescription.split("/from");
        String description = descriptions[0].trim();
        String[] eventTime = descriptions[1].split("/to");
        String eventStart = eventTime[0].trim();
        String eventEnd = eventTime[1].trim();

        Task event = new Event(description, eventStart, eventEnd);
        tasks.add(event);
        Ui.printTaskAddedMessage(event);
    }

    public void deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex-1);
        tasks.remove(task);
        Task.decreaseTaskCountByOne();
        Ui.printTaskRemovedMessage(task);
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex-1).markTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_MARK);
        System.out.println("  " + tasks.get(taskIndex-1).getDetails());
        System.out.println(LINE);
    }

    public void unMarkTask(int taskIndex) {
        tasks.get(taskIndex-1).unMarkTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_UNMARK);
        System.out.println("  " + tasks.get(taskIndex-1).getDetails());
        System.out.println(LINE);
    }
}
