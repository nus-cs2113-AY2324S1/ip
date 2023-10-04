import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
    public void remove(Task task){
        this.tasks.remove(task);
    }

    /**
     * This method attempts to add a Todo task into the TaskList.
     * It will check whether the description of the task is empty before
     * creating a ToDo object and add it into the TaskList.
     *
     * @param description Details regarding the todo task.
     * @throws DukeException If the description of the todo task is empty.
     */
    public void addTodo(String description) throws DukeException {
        if (description.isBlank()) { // Check if description is empty
            throw new DukeException("Description of a todo cannot be empty");
        }
        tasks.add(new ToDo(description));

    }

    /**
     * This method attempts to add a deadline task into the TaskList.
     * It will check whether the description of the task is empty before
     * extracting the deadline of the task. It will then create a new Deadline
     * object and add it into the TaskList.
     *
     * @param description Details regarding the deadline task.
     * @throws DukeException If the description of the deadline task is empty
     *                       or when the /by keyword is not provided.
     */
    public void addDeadline(String description) throws DukeException {
        try {
            String[] deadlineTokens = description.split("/by");
            description = deadlineTokens[0].trim();
            String by = deadlineTokens[1].trim();
            if (description.isBlank() | by.isBlank()) { // Check if description is empty
                throw new DukeException("Wrong format! Expected format: deadline DESCRIPTION /by TIME");
            }
            Deadline deadline = new Deadline(description, by);
            tasks.add(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong format! Expected format: deadline DESCRIPTION /by TIME");
        }

    }

    /**
     * This method attempts to add an event task to the TaskList.
     * It will check whether the description of the task is empty before extracting
     * the dates/time for from when the event start to when the event ends. It then
     * creates a Event object and add it into the TaskList.
     *
     * @param description Details regarding the Event task.
     * @throws DukeException If the description of the Event task is empty
     *                       or when the /from and /to keywords are the provided.
     */
    public void addEvent(String description) throws DukeException {
        try {
            String[] eventTokens = description.split("/from");
            description = eventTokens[0].trim();
            String[] fromAndToTokens = eventTokens[1].split("/to");
            String from = fromAndToTokens[0].trim();
            String to = fromAndToTokens[1].trim();
            if (description.isBlank() | from.isBlank() | to.isBlank()) { // Check if format is correct
                throw new DukeException("Wrong format! Expected format: "
                                         + "event DESCRIPTION /from START_TIME /to END_TIME");
            }
            Event event = new Event(description, from, to);
            tasks.add(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong format! Expected format: event DESCRIPTION /from START_TIME /to END_TIME");
        }

    }

    /**
     * This method attempts to mark a task in the TaskList
     * It will check whether the task index provided by the user is valid before marking the task as
     * the respective isDone argument.
     *
     * @param taskID Index of the task to be marked.
     * @param isDone True to mark the task as done and False to mark the task as undone.
     * @throws DukeException if task index is not found or task index in not an int.
     */
    public void markTask(String taskID, boolean isDone) throws DukeException {
        try {
            int index = Integer.parseInt(taskID);
            tasks.get(index-1).setDone(isDone);
        } catch (NullPointerException | NumberFormatException e) {
            throw new DukeException("Oops! An integer taskID is expected");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Task " + taskID + " does not exist");
        }
    }

    /**
     * This method attempts to delete a task based on the task index from the TaskList
     * It will check whether the task index provided by the user exist before deleting the task.
     *
     * @param taskID Index of task to be deleted.
     * @return The removed task to be printed by Ui.
     * @throws DukeException If task index is not an integer or task index does not exist.
     */
    public Task deleteTask(String taskID) throws DukeException {
        try {
            int index = Integer.parseInt(taskID);
            Task removedTask = tasks.get(index-1);
            tasks.remove(index-1);
            return removedTask;
        } catch (NullPointerException | NumberFormatException e) {
            throw new DukeException("Oops! An integer taskID is expected");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Task " + taskID + " does not exist");
        }
    }

    /**
     * This method attempts to find the tasks with descriptions that matches the keyword provided
     * It first check if the keyword provided is valid before going through the TaskList. For every
     * task, it splits the description into substrings and compare word by word with the keyword.
     *
     * @param keyword Keyword to be searched for.
     * @return A list of tasks that matches the keyword for Ui object to print.
     * @throws DukeException If keyword is not a word or null.
     */
    public ArrayList<Task> find(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException("Keyword cannot be empty");
        }
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            String[] substrs = task.getDescription().split("\\s+");
            for (String substr : substrs) {
                if (substr.equals(keyword)) {
                    matchingTasks.add(task);
                    break;
                }
            }
        }
        return matchingTasks;
    }
}
