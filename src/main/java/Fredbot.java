import java.util.Scanner;

public class Fredbot {
    public static void addTasks(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Task(task);
        Task.setNumTask(numTask+1);
    }
    public static void printTasks(Task[] tasks) {
        StringBuilder tasklist = new StringBuilder();
        int numTask = Task.getNumTask();
        for (int i = 0; i < numTask; i++) {
            tasklist.append("    ").append(i + 1).append(". ").append(tasks[i].getTaskDesc()).append("\n");
        }
        printMessage(tasklist.toString());
    }
    public static void printMessage(String message) {
        System.out.print("    ____________________________________________________________\n");
        System.out.println(message);
        System.out.println("    ____________________________________________________________\n");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Task[] tasks = new Task[100];
        String greeting = "     Hello! I'm Fredbot\n" +
                "     What can I do for you?";
        String farewell = "     Bye. Hope to see you again soon!";
        printMessage(greeting);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printTasks(tasks);
            }
            else {
                addTasks(tasks, line);
                printMessage("    added: " + line);
            }
            line = in.nextLine();
        }
        printMessage(farewell);
    }
}
