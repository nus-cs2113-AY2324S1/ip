import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String HORIZONTAL_LINE = "--------------------------------------------";
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        introduceBot();
        handleCommands();
        farewellBot();
    }

    public static void handleCommands() {
        Scanner in = new Scanner(System.in);
        String input;

        do {
            input = in.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();
            String argument = null;
            if (parts.length > 1) {
                argument = parts[1];
            }
            try {
                switch (command) {
                case "list":
                    printList();
                    break;
                case "mark":
                    editTask(argument, true);
                    break;
                case "unmark":
                    editTask(argument, false);
                    break;
                case "todo":
                    addToDo(argument);
                    break;
                case "deadline":
                    addDeadline(argument);
                    break;
                case "event":
                    addEvent(argument);
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                System.out.println("Oops, seems like I don't know this command. Please provide a valid command!");
            }
            System.out.println(HORIZONTAL_LINE);
        } while (!input.equalsIgnoreCase("bye"));
    }

    public static void introduceBot(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Lexi";

        System.out.println(logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name);
        System.out.println("How can I help you buddy?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void farewellBot(){
        System.out.println("Have a wonderful day! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }

    public static void editTask(String argument, boolean done){
        /*if (index < 1 || index > tasks.size()){
            System.out.println("I am sorry, but this task does not exist");
            return;
        }*/
        try {
            int index = Integer.parseInt(argument);
            tasks.get(index - 1).setDone(done);
            if (done){
                System.out.println("Great! I have marked this task as done:");
            } else{
                System.out.println("Alright, I have marked this task as not done:");
            }
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            System.out.println("This task id does not exist, please provide a valid task number!");
        }
    }

    public static void printTaskAddedMessage(Task task){
        System.out.println("Ok, I have added the following task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public static void addToDo(String argument){
        Task todo = new Todo(argument);
        tasks.add(todo);
        printTaskAddedMessage(todo);
    }

    public static void addDeadline(String argument){
        String dueDate = argument.split(" /by ")[1];
        String description = argument.split(" /by ")[0];
        Task deadline = new Deadline(description, dueDate);
        tasks.add(deadline);
        printTaskAddedMessage(deadline);
    }

    public static void addEvent(String argument){
        String description = argument.split(" /from ")[0];
        String startDate = argument.split(" /from ")[1].split(" /to ")[0];
        String endDate = argument.split(" /from ")[1].split(" /to ")[1];
        Task event = new Event(description, startDate, endDate);
        tasks.add(event);
        printTaskAddedMessage(event);
    }
}
