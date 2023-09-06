import java.util.Scanner;
public class Chatty {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Chatty!\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;
        int index;
        loop:
        while (!input.equalsIgnoreCase("bye")) {
            String[] words = input.split(" ");
            String command = words[0].toLowerCase();
            switch (command) {
            case "list":
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue loop;
            case "mark":
                index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].mark();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index].getDescription());
                break;
            case "unmark":
                index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmark();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[index].getDescription());
                break;
            case "todo":
                String todoDescription = input.substring(5);
                tasks[taskCount] = new Todo(todoDescription);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][ ] " + todoDescription);
                break;
            case "deadline":
                int byIndex = input.indexOf("/by");
                String deadlineDescription = input.substring(9, byIndex).trim();
                String by = input.substring(byIndex + 3).trim();
                tasks[taskCount] = new Deadline(deadlineDescription, by);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("[D][ ] " + deadlineDescription + " (by: " + by + ")");
                break;
            case "event":
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String eventDescription = input.substring(6, fromIndex).trim();
                String from = input.substring(fromIndex + 5, toIndex).trim();
                String to = input.substring(toIndex + 3).trim();
                tasks[taskCount] = new Event(eventDescription, from, to);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("[E][ ] " + eventDescription + " (from: " + from + " to: " + to + ")");
                break;
            default:
                System.out.println("____________________________________________________________");
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
                break;
            }
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________\n");
            input = scanner.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
