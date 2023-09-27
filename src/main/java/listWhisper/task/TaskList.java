package listWhisper.task;
import listWhisper.exceptions.DescriptionFormatException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId);
    }

    //Actions
    public Todo addTodo(String description) {
        Todo todo = new Todo(description);
        this.tasks.add(todo);
        return todo;
    }

    public Deadline addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        this.tasks.add(deadline);
        return deadline;
    }

    public Event addEvent(String description, String startTime, String endTime) {
            Event event = new Event(description, startTime, endTime);
            this.tasks.add(event);
            return event;
    }

    public void mark(int taskId) {
        Task task = this.tasks.get(taskId);
        task.setDone(true);
    }

    public void unmark(int taskId) {
        Task task = this.tasks.get(taskId);
        task.setDone(true);
    }

    public Task delete(int taskId) {
            Task task = this.tasks.get(taskId);
            this.tasks.remove(taskId - 1);
            return task;
    }

    public String prepareSaveList() {
        StringBuilder listOfFormattedTasks = new StringBuilder();
        for (Task task : this.tasks) {
            listOfFormattedTasks.append(task.formatAsInput()).append("\n");
        }
        return listOfFormattedTasks.toString();
    }

    // Miscellaneous methods
    private int getTaskId(String input) throws DescriptionFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 1) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to mark/unmark a task: "
                            + "mark/unmark [task id]");
        }
        return Integer.parseInt(splitInput[1]);
    }

    @Override
    public String toString() {
        if (this.getSize() == 0) {
            return "You have no task in your list!\n";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(this.tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }
}
