import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = "Simon";

        //Print out greeting when user starts the program.
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
                    System.out.println("\t" + (i + 1) + "." + tasks[i]);
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
            else if (userInput.startsWith("todo ")) {
                tasks[Task.getNumberOfTask()] = new Todo(userInput.substring(5));
                System.out.println("\t" + line);
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
                System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
                System.out.println("\t" + line);
                userInput = in.nextLine();
            }
            else if (userInput.startsWith("event ")) {
                int fromIndex = userInput.indexOf((" /from"));
                int toIndex = userInput.indexOf(" /to");
                String description = userInput.substring(6, fromIndex);
                String from = userInput.substring(fromIndex + 7, toIndex);
                String to = userInput.substring(toIndex + 5);
                tasks[Task.getNumberOfTask()] = new Event(description, from, to);
                System.out.println("\t" + line);
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
                System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
                System.out.println("\t" + line);
                userInput = in.nextLine();

            }
            else if (userInput.startsWith("deadline ")) {
                int byIndex = userInput.indexOf((" /by"));
                String description = userInput.substring(9, byIndex);
                String by = userInput.substring(byIndex + 5);
                tasks[Task.getNumberOfTask()] = new Deadline(description, by);
                System.out.println("\t" + line);
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t  " + tasks[Task.getNumberOfTask() - 1]);
                System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
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
