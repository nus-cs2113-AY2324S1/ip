import java.util.ArrayList;

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

    public void addTodo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Description of a todo cannot be empty");
        }
        tasks.add(new ToDo(description));
    }

    public void addDeadline(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Description of a deadline cannot be empty");
        }
        try {
            String[] deadlineTokens = description.split("/by");
            description = deadlineTokens[0].trim();
            String by = deadlineTokens[1].trim();
            Deadline deadline = new Deadline(description, by);
            tasks.add(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong format! Expected format: deadline [task] /by [time]");
        }

    }

    public void addEvent(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Description of a event cannot be empty");
        }
        try {
            String[] eventTokens = description.split("/from");
            description = eventTokens[0].trim();
            String[] fromAndToTokens = eventTokens[1].split("/to");
            String from = fromAndToTokens[0].trim();
            String to = fromAndToTokens[1].trim();
            Event event = new Event(description, from, to);
            tasks.add(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong format! Expected format: event [task] /from [time] /to [time]");
        }

    }

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

    public Task deleteTask(TaskList taskList, String taskID) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
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
}
