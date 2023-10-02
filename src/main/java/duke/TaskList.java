package duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static void addTaskToList(String line, ArrayList<Task> tasks) throws DukeException {
        String[] parts = line.split(" ", 2);
        String command = parts[0];
        String taskDescription = parts.length > 1 ? parts[1] : "";
        switch (command) {
        case "todo":
            if (taskDescription.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.add(new TodoTask(taskDescription));
            break;
        case "deadline":
            if (taskDescription.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            tasks.add(new DeadlineTask(taskDescription));
            break;
        case "event":
            if (taskDescription.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            tasks.add(new EventTask(taskDescription));
            break;
        default:
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void deleteTask(String line, ArrayList<Task> tasks) throws DukeException {
        int taskIndex = Integer.parseInt(line.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:\n" + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("Task not found. Please provide a valid task number.");
        }
    }


    public static void unmarkTask(String line, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(line.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    public static void markTask(String line, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(line.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        }
    }
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

