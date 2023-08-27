import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm LinguoBot");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int itemCount = 0;

        while (true) {
            String line = in.nextLine();
            Task newTask = new Task(line);

            if (line.equals("list")) {
                System.out.println("-------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0 ; i<itemCount; i++) {
                    System.out.println(i + 1 + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
                }
                System.out.println("-------------------------");
            }

            if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < itemCount) {
                    taskList[taskIndex].markAsDone();
                    System.out.println("-------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].getDescription());
                    System.out.println("-------------------------");
                } else {
                    System.out.println("-------------------------");
                    System.out.println("Invalid task index.");
                    System.out.println("-------------------------");
                }
            }
            if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;
//                String indexStr = line.replaceAll("[^0-9]", ""); // Extract digits from the input
//                int taskIndex = Integer.parseInt(indexStr) - 1;
//                System.out.println("Debug: Task index = " + taskIndex);
                if (taskIndex >= 0 && taskIndex < itemCount) {
                    taskList[taskIndex].markAsUndone();
                    System.out.println("-------------------------");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].getDescription());
                    System.out.println("-------------------------");
                } else {
                    System.out.println("-------------------------");
                    System.out.println("Invalid task index.");
                    System.out.println("-------------------------");
                }
            }
            if (line.contains("bye")) {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                break;
            }
            else if (!line.equals("list") && !line.startsWith("mark") && !line.startsWith("unmark") && !line.contains("bye")) {
                System.out.println("-------------------------");
                System.out.println("added: " + newTask.description);
                System.out.println("-------------------------");
                taskList[itemCount] = newTask;
                itemCount++;
            }
        }
    }
}