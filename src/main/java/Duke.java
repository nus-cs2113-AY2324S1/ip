import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];

    public static void addTask(String command, String type) {
        String task = command.replace(type, "").strip();
        System.out.println("Got it. I've added this task: " + task);
        tasks[Task.getTaskCount()] = new Task(task);
    }

    public static void listTask() {
        for(int i=0; i<Task.getTaskCount(); i++) {
            System.out.print(i+1);
            if (tasks[i].getDone()) {
                System.out.print(". [X] ");
            } else {
                System.out.print(". [ ] ");
            }
            System.out.println(tasks[i].getName());
        }
    }

    public static void toggleDone(String taskNumber, boolean isDone) {
        try {
            int taskId = Integer.parseInt(taskNumber);
            if (taskId > Task.getTaskCount() || taskId <= 0) {
                System.out.println("Task out of range!");
            } else {
                tasks[taskId-1].setDone(isDone);
                if (isDone) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + tasks[taskId-1].getName());
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + tasks[taskId-1].getName());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    public static void processCommand(String line) {
        Scanner textIn = new Scanner(System.in);
        String command = "";
        while (true) {
            command = textIn.nextLine();
            if (command.equals("bye")) {
                break;
            }
            String[] tokens = command.split(" ");
            System.out.println(line);
            switch (tokens[0]) {
            case "list": listTask();
                break;
            case "todo":
            case "deadline":
            case "event": addTask(command, tokens[0]);
                break;
            case "mark": toggleDone(tokens[1], true);
                break;
            case "unmark": toggleDone(tokens[1], false);
                break;
            default:
                break;
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm soccat!\nWhat can I do for you?");
        System.out.println(line);
        processCommand(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
