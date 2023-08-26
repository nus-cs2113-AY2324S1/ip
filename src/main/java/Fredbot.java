import java.util.Scanner;

public class Fredbot {
    public static int numTask = 0;
    public static void addTasks(String tasks[], String task) {
        tasks[numTask] = task;
        numTask++;
    }
    public static void printTasks(String tasks[]) {
        StringBuilder tasklist = new StringBuilder();
        for (int i = 0; i < numTask; i++) {
            tasklist.append("    ").append(i + 1).append(". ").append(tasks[i]).append("\n");
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
        String tasks[] = new String[100];
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
