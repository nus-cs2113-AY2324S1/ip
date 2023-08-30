import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];

    public static void main(String[] args) {
        System.out.println("Hello! I'm KevBot");
        System.out.println("What can I do for you?");


        Scanner in = new Scanner(System.in);
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                printTasks();
            } else {
                addTask(line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printTasks() {
        for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void addTask(String task) {
        for (int i = 0; i < tasks.length; i ++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                break;
            }
        }
        System.out.println("added: " + task);
    }
}
