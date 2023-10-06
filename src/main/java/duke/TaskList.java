package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("☹ OOPS!!! The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).markAsDone();
        } else {
            System.out.println("☹ OOPS!!! Please provide a valid task number.");
        }
    }

    public void markTaskAsUndone(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index));
        } else {
            System.out.println("☹ OOPS!!! Please provide a valid task number.");
        }
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public Task deleteTask(int index) {
        if (isValidIndex(index)) {
            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n" + removedTask);
        } else {
            System.out.println("☹ OOPS!!! Please provide a valid task number.");
        }
        return null;
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
