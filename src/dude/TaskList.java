package dude;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            ui.showMessage("You have no tasks in your list!");
        } else {
            ui.showTasks(tasks);
        }
    }

    public void addTodoTask(String input) throws DudeException {
        validateInput(input, 5, "☹ OOPS!!! The description of a todo cannot be empty.");

        String taskDescription = input.substring(5).trim();
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);

        ui.showAddedTask(newTask, tasks.size());
        storage.saveToFile(tasks);
    }

    public void addDeadlineTask(String input) throws DudeException {
        validateInput(input, 9, "☹ OOPS!!! The description of a deadline cannot be empty.");

        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DudeException("☹ OOPS!!! Please specify a deadline using /by.");
        }

        String taskDescription = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 4).trim();

        Deadline newDeadline = new Deadline(taskDescription, by);
        tasks.add(newDeadline);

        ui.showAddedTask(newDeadline, tasks.size());
        storage.saveToFile(tasks);
    }

    public void addEventTask(String input) throws DudeException {
        validateInput(input, 6, "☹ OOPS!!! The description of an event cannot be empty.");

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new DudeException("☹ OOPS!!! Please specify event time using /from and /to.");
        }

        String taskDescription = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 6, toIndex).trim();
        String to = input.substring(toIndex + 4).trim();

        Event newEvent = new Event(taskDescription, from, to);
        tasks.add(newEvent);

        ui.showAddedTask(newEvent, tasks.size());
        storage.saveToFile(tasks);
    }

    public void markOrUnmarkTask(String input, boolean mark) throws DudeException {
        int index = parseTaskIndex(input);
        Task task = tasks.get(index);
        task.setDone(mark);

        ui.showMarkedTask(task, mark);
        storage.saveToFile(tasks);
    }

    public void deleteTask(String input) throws DudeException {
        int index = parseTaskIndex(input);
        Task removedTask = tasks.remove(index);

        ui.showRemovedTask(removedTask, tasks.size());
        storage.saveToFile(tasks);
    }

    private int parseTaskIndex(String input) throws DudeException {
        String[] arrOfInput = input.split(" ");
        if (arrOfInput.length < 2) {
            throw new DudeException("Please specify the task index.");
        }

        try {
            int index = Integer.parseInt(arrOfInput[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DudeException("Task index out of range.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new DudeException("Invalid task index format.");
        }
    }

    private void validateInput(String input, int minLength, String errorMessage) throws DudeException {
        if (input.length() <= minLength || input.substring(minLength).trim().isEmpty()) {
            throw new DudeException(errorMessage);
        }
    }
}
