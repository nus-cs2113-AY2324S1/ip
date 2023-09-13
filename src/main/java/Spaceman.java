import java.util.Scanner;

public class Spaceman {
    public static void main(String[] args) {
        String logo = "  ____  _____   ___    _____ _____ __    __   ___   __   __\n"
            + "/     /|  __ \\ /   \\  /   __|     |  \\  /  | /   \\ |  \\ |  |\n"
            + "\\   __\\| |__) |  _  \\|   /  |   __|   \\/   |/  _  \\|   \\|  |\n"
            + " \\__   |  ___/  |_|  |  |   |   __|        |  |_|  |       |\n"
            + "/      | |   |   _   |   \\__|     |   __   |   _   |       |\n"
            + "|____ /|_|   |__| |__|\\_____|_____|__|  |__|__| |__|__|\\___|\n";

        Task[] tasks = new Task[100];

        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Spaceman");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                printList(tasks);
            } else if (text.startsWith("mark")) {
                String[] markDetails = text.split(" ");
                markTask(tasks, Integer.parseInt(markDetails[1]));
            } else if (text.startsWith("unmark")) {
                String[] unMarkDetails = text.split(" ");
                unMarkTask(tasks, Integer.parseInt(unMarkDetails[1]));
            } else {
                addToList(tasks, text);
            }
            text = sc.nextLine();
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------");
    }

    public static void addToList(Task[] taskList, String taskDescription) {
        Task task;
        String description;
        int descriptionIndex = taskDescription.indexOf(" ") + 1;

        if (taskDescription.startsWith("todo")) {
            description = taskDescription.substring(descriptionIndex);
            task = new Todo(description);
        } else if (taskDescription.startsWith("deadline")) {
            int byIndex = taskDescription.indexOf("/");
            String by = taskDescription.substring(byIndex+4);
            description = taskDescription.substring(descriptionIndex, byIndex-1);
            task = new Deadline(description, by);
        } else {
            int startIndex = taskDescription.indexOf("/");
            int endIndex = taskDescription.indexOf("/", startIndex+1);
            String startTime = taskDescription.substring(startIndex+6, endIndex);
            String endTime = taskDescription.substring(endIndex+4);
            description = taskDescription.substring(descriptionIndex, startIndex);
            task = new Event(description, startTime, endTime);
        }

        taskList[Task.getTaskCount()-1] = task;
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDescription());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    public static void printList(Task[] tasks) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++){
            System.out.println(i + 1 + ". " + tasks[i].getDescription());
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void markTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex-1].markTask();
        System.out.println("------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[taskIndex-1].getDescription());
        System.out.println("------------------------------------------------------------");
    }

    public static void unMarkTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex-1].unMarkTask();
        System.out.println("------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[taskIndex-1].getDescription());
        System.out.println("------------------------------------------------------------");
    }

}
