package task;
import exceptions.DescriptionFormatException;

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

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
