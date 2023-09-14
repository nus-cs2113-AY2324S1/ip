import sun.exception.InvalidActionException;
import sun.exception.IncompleteDescriptionException;
import sun.exception.TasksOverflowException;
import sun.exception.InvalidTaskIndexException;
import sun.task.Task;
import sun.task.Deadline;
import sun.task.Todo;
import sun.task.Event;
import java.util.Scanner;

public class Sun {
    public static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____               \n"
                + "| ___| _   _  ______ \n"
                + "| \\__ | | | || /--\\ |\n"
                + " \\___|| |_| || |  | |\n"
                + "/____/ \\__,_||_|  |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Sun!");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Task[] tasks = new Task[100];
        int taskCount = 0; // Keep track of the number of tasks added

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                taskCount = getInput(command, tasks, taskCount);
            } catch (InvalidActionException e) {
                System.out.println(line);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            } catch (IncompleteDescriptionException e) {
                System.out.println(line);
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println(line);
            } catch (InvalidTaskIndexException e) {
                System.out.println(line);
                System.out.println("☹ OOPS!!! The task index is Invalid :((");
                System.out.println(line);
            } catch (TasksOverflowException e) {
                System.out.println(line);
                System.out.println("☹ OOPS!!! The maximum number of Task is 100 :((");
                System.out.println(line);
            }

            command = scanner.nextLine();
            System.out.println(line);
            System.out.println("What else can I do for you?");
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();
    }

    public static int getInput(String command, Task[] tasks, int taskCount) throws InvalidTaskIndexException,
            TasksOverflowException, InvalidActionException, IncompleteDescriptionException {
        String[] actionAndTask = command.split(" ");
        String action = actionAndTask[0];

        switch (action) {
            case "list":
                printList(tasks, taskCount);
                break;
            case "mark":
                int taskIndex = Integer.parseInt(actionAndTask[1]);
                if (taskIndex >= 1 && taskIndex <= taskCount) {
                    markTask(tasks, taskIndex - 1);
                } else {
                    throw new InvalidTaskIndexException();
                    //System.out.println("Invalid task index.");
                }
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(actionAndTask[1]);
                if (unmarkIndex >= 1 && unmarkIndex <= taskCount) {
                    unMarkTask(tasks, unmarkIndex - 1);
                } else {
                    throw new InvalidTaskIndexException();
                    //System.out.println("Invalid task index.");
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (taskCount < tasks.length) {
                    tasks[taskCount] = addToList(command, taskCount);
                    taskCount++;
                } else {
                    throw new TasksOverflowException();
                    //System.out.println("Task list is full. Cannot add more tasks.");
                }
                break;
            default:
                throw new InvalidActionException();
                //System.out.println("Invalid command.");
        }

        return taskCount;
    }

    public static void printList(Task[] tasks, int taskCount) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
        System.out.println(line);
    }

    public static void markTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[taskIndex].getDescription());
        System.out.println(line);
    }

    public static void unMarkTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsNotDone();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[taskIndex].getDescription());
        System.out.println(line);
    }

    public static Task addToList(String command, int taskCount) throws IncompleteDescriptionException {
        Task task = null;
        String[] actionAndTask = command.split(" ");
        if (actionAndTask.length < 2) {
            throw new IncompleteDescriptionException();
        } else if (command.startsWith("todo")) {
            task = new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            String deadlineCommand = command.substring(9);
            String[] parts = deadlineCommand.split("/by");
            task = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (command.startsWith("event")) {
            String deadlineCommand = command.substring(6);
            String[] parts = deadlineCommand.split("/from|/to", 3);
            task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        }

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDescription());
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println(line);

        return task;
    }
}
