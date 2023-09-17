package tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.DukeUi;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/**
 *
 * The manager of the task list - adding, marking or unmarking tasks.
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    static DukeUi ui = new DukeUi();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTaskDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.setDone(true);
        }
    }

    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.setDone(false);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    /**
     *
     * Method for when user inputs todo.
     *
     * @param input The input string from user
     * @param inputWords The description of todo task
     */
    public void addToDoTask(String input, String[] inputWords) {
        try {
            String todoDescription = input.substring(5);
            ToDo todo = new ToDo(todoDescription);
            addTask(todo);
            ui.printAddedTask(todo, this);
        } catch (IndexOutOfBoundsException e) {
            ui.printToDoTaskException();
        }
    }

    /**
     *
     * Method for when user inputs deadline.
     *
     * @param input The input string from user
     * @param inputWords The description of deadline task
     */
    public void addDeadline(String input, String[] inputWords) {
        if (inputWords.length > 2 && input.contains("/by")) {
            int byIndex = input.indexOf("/by");
            String deadlineDescription = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            Deadline deadline = new Deadline(deadlineDescription, by);
            addTask(deadline);
            ui.printAddedTask(deadline, this);
        } else {
            ui.printDeadlineException();
        }
    }

    /**
     *
     * Method for when user inputs event.
     *
     * @param input The input string from user
     * @param inputWords The description of event task
     */
    public void addEvent(String input, String[] inputWords) {
        if (inputWords.length > 3 && input.contains("/from")
                && input.contains("/to")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String eventDescription = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 6, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            Event event = new Event(eventDescription, from, to);
            addTask(event);
            ui.printAddedTask(event, this);
        } else {
            ui.printEventException();
        }
    }

    /**
     *
     * Method to mark a specified task as done
     *
     * @param inputWords String input by user
     */
    public void markAsDone(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]);
            markTaskDone(taskIndex);
            ui.printMarkedTask(taskIndex, this);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please use 'mark <number>'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify the task number");
        }
    }

    /**
     *
     * Method to unmark a specified task as done.
     *
     * @param inputWords String input by user
     */
    public void unmark(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]);
            unmarkTask(taskIndex);
            ui.printUnmarkedTask(taskIndex, this);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please use 'unmark + number'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify the task number");
        }
    }

    /**
     *
     * Method to delete a task.
     *
     * @param inputWords String input by user
     */
    public void deleteTask(String[] inputWords) {
        int taskIndex = Integer.parseInt(inputWords[1]);
        try {
            if (isValidIndex(taskIndex)) {
                Task removedTask = tasks.remove(taskIndex - 1);
                ui.printRemovedTask(removedTask, this);
            } else {
                System.out.println("Invalid task index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please use 'delete <number>'.");
        }
    }
}