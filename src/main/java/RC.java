import java.util.ArrayList;
import java.util.Scanner;

public class RC {
    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String line;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                printTaskList(tasks);
            } else if (line.startsWith("mark")) {
                String taskIndex = line.substring(line.length() - 1);
                int taskNum = Integer.parseInt(taskIndex) - 1;
                tasks.get(taskNum).markAsDone();
            } else if (line.startsWith("unmark")) {
                String taskIndex = line.substring(line.length() - 1);
                int taskNum = Integer.parseInt(taskIndex) - 1;
                tasks.get(taskNum).unmarkTask();
            } else {
                String description = line;
                if (line.startsWith("todo")) {
                    description = line.substring(5);
                }
                tasks.add(new Todo(description));
                tasks.get(tasks.size() - 1).printAddedTask();
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
