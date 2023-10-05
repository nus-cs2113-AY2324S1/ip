package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks =  new ArrayList<>();
    public TaskList() {}

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String markTask(int index, boolean isDone) {
        final StringBuilder formatted = new StringBuilder();
        tasks.get(index).setStatus(isDone);

        if (isDone) {
            formatted.append("Nice! I've marked this task as done:").append("\n");
        } else {
            formatted.append("OK, I've marked this task as not done yet:").append("\n");
        }
        formatted.append(tasks.get(index).getFormattedTask());

        return formatted.toString();
    }


    public String addTask(Task task) {
        tasks.add(task);

        return "Got it. I've added this task:\n" + task.getFormattedTask() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public String removeTask(int idx) {
        Task removedTask = tasks.remove(idx);

        return "Got it. I've removed this task:\n" + removedTask.getFormattedTask() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public String getIndexedTasks() {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < tasks.size() && tasks.get(i) != null; i++) {
            formatted.append(i + 1).append(". ").append(tasks.get(i).getFormattedTask()).append("\n");
        }

        return formatted.toString();
    }

    public String getSerializedTasks() {
        StringBuilder formatted = new StringBuilder();
        for (Task task : tasks) {
            formatted.append(task.getSerializedString()).append("\n");
        }

        return formatted.toString();
    }
}
