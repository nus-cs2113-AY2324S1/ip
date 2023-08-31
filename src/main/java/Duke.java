import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static List<Task> tasks = new ArrayList<Task>();

    public static void print_tasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t\t%d. [%s] %s\n", i + 1, tasks.get(i).getStatusIcon(), tasks.get(i).getDescription());
        }
    }

    public static void main(String[] args) {
        System.out.println("\tHello! I'm Richard\n");
        System.out.println("\tWhat can I do for you ?\n");

        Scanner input = new Scanner(System.in);

        String user_input;

        do {
            user_input = input.nextLine();
            if (user_input.equals("list")) {
                print_tasks();
            } else if (user_input.startsWith("mark ")) {
                int index = Integer.parseInt(user_input.split(" ")[1]);
                if (index < 0 || index > tasks.size()) {
                    System.out.println("\tPlease enter a valid index.");
                    continue;
                }
                tasks.get(index - 1).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t\t [%s] %s\n", tasks.get(index - 1).getStatusIcon(), tasks.get(index - 1).getDescription());
            } else if (user_input.startsWith("unmark ")) {
                int index = Integer.parseInt(user_input.split(" ")[1]);
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Please enter a valid index.");
                    continue;
                }
                tasks.get(index - 1).markAsUndone();
                System.out.println("\tOk! I've marked this task as not done yet:");
                System.out.printf("\t\t [%s] %s\n", tasks.get(index - 1).getStatusIcon(), tasks.get(index - 1).getDescription());
            } else {
                tasks.add(new Task(user_input));
                System.out.printf("\tadded: %s\n", user_input);
            }
        }
        while (!user_input.equals("bye"));

        System.out.println("\tBye hope to see you again\n");
    }
}
