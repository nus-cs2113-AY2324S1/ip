import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];

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
            } else if (line.contains("mark")) {
                int divider = line.indexOf(" ");
                int idx = Integer.parseInt(line.substring(divider + 1)) - 1;
                if (idx < 0 || tasks[idx] == null) {
                    System.out.println("Sorry! That's not a valid task");
                    continue;
                }

                markTask(idx, line.startsWith("mark"));
            } else {
                addTask(line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void markTask(int index, boolean isDone) {
        tasks[index].setStatus(isDone);
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks[index].getFormattedTask());
    }

    public static void printTasks() {
        for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getFormattedTask());
        }
    }

    public static void addTask(String task) {
        for (int i = 0; i < tasks.length; i ++) {
            if (tasks[i] == null) {
                tasks[i] = new Task(task);
                break;
            }
        }
        System.out.println("added: " + task);
    }
}
