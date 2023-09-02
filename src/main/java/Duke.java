import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = "Simon";
        System.out.println("\t" + line);
        System.out.println("\t" + "Hello I'm " + logo);
        System.out.println("\t" + "What can I do for you?");
        System.out.println("\t" + line);
        String userInput;
        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("\t" + line);
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < Task.getNumberOfTask(); i++) {
                    System.out.print("\t" + (i + 1) + ".[" + tasks[i].getStatusIcon() + "]");
                    System.out.println(" " + tasks[i].getDescription());
                }
                System.out.println("\t" + line);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("mark ")) {
                int target = Integer.parseInt(userInput.substring(5)) - 1;
                tasks[target].markAsDone();
                System.out.println("\t" + line);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  [X] " + tasks[target].getDescription());
                System.out.println("\t" + line);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("unmark ")) {
                int target = Integer.parseInt(userInput.substring(7)) - 1;
                tasks[target].unmarkAsDone();
                System.out.println("\t" + line);
                System.out.println("\tOkay, I've marked this task as not done yet:");
                System.out.println("\t  [] " + tasks[target].getDescription());
                System.out.println("\t" + line);
                userInput = in.nextLine();
            }
            else {
                tasks[Task.getNumberOfTask()] = new Task(userInput);
                System.out.println("\t" + line);
                System.out.println("\t" + "added: " + userInput);
                System.out.println("\t" + line);
                userInput = in.nextLine();
            }
        }
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
