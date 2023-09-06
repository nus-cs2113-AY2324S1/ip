import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String horizontalLine = "--------------------------------------------";
    public static void main(String[] args) {
        introduceBot();

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        List<Task> tasks = new ArrayList<Task>();
        do {
            String command = input.split(" ", 2)[0];
            System.out.println(horizontalLine);
            switch (command.toLowerCase()) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i+1) + "." + tasks.get(i).toString());
                }
                break;
            case "mark":
                String argument = input.split(" ", 2)[1];
                int index = Integer.parseInt(argument);
                if (index < 1 || index > tasks.size()) {
                    System.out.println("I am sorry, but this task does not exist");
                } else {
                    System.out.println("Great! I have marked this task as done:");
                    tasks.get(index-1).setDone(true);
                    System.out.println(tasks.get(index-1).toString());
                }
                break;
            case "unmark":
                argument = input.split(" ", 2)[1];
                index = Integer.parseInt(argument);
                if (index < 1 || index > tasks.size()) {
                    System.out.println("I am sorry, but this task does not exist");
                } else {
                    System.out.println("Alright, I have marked this task as not done:");
                    tasks.get(index - 1).setDone(false);
                    System.out.println(tasks.get(index - 1).toString());
                }
                break;
            case "todo":
                argument = input.split(" ", 2)[1];
                System.out.println("Ok, I have added the following task:");
                Task todo = new Todo(argument);
                tasks.add(todo);
                System.out.println("   " + todo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                argument = input.split(" ", 2)[1];
                System.out.println("Ok, I have added the following task:");
                String dueDate = argument.split(" /by ")[1];
                String description = argument.split(" /by ")[0];
                Task deadline = new Deadline(description, dueDate);
                tasks.add(deadline);
                System.out.println("   " + deadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "event":
                argument = input.split(" ", 2)[1];
                System.out.println("Ok, I have added the following task:");
                description = argument.split(" /from ")[0];
                String startDate = argument.split(" /from ")[1].split(" /to ")[0];
                String endDate = argument.split(" /from ")[1].split(" /to ")[1];
                Task event = new Event(description, startDate, endDate);
                tasks.add(event);
                System.out.println("   " + event.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                break;
            default:
            }
            System.out.println(horizontalLine);
            input = in.nextLine();
    } while (!input.equalsIgnoreCase("bye"));

        System.out.println(horizontalLine);
        System.out.println("Have a wonderful day! Hope to see you again soon!");
        System.out.println(horizontalLine);

    }

    public static void introduceBot(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String name = "Lexi";

        System.out.println(logo);

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + name);
        System.out.println("How can I help you buddy?");
        System.out.println(horizontalLine);
    }
}
