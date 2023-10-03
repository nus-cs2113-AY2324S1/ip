import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void addToTasks(String description, Type type) {
        switch (type) {
        case TODO:
            tasks.add(new ToDos(description));
            break;
        case DEADLINE:
            try {
                String[] deadlineTokens = description.split("/by");
                description = deadlineTokens[0].trim();
                String by = deadlineTokens[1].trim();
                tasks.add(new Deadlines(description, by));
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\t/by could not be found");
                return;
            }
        case EVENT:
            try {
                String[] eventTokens = description.split("/from");
                description = eventTokens[0].trim();
                eventTokens = eventTokens[1].split("/to");
                String from = eventTokens[0].trim();
                String to = eventTokens[1].trim();
                tasks.add(new Events(description, from, to));
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tBoth /from and /to are required");
                return;
            }
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + tasks.get(tasks.size()-1));
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
        //Append to file
    }
    public static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\tYou do not have any task in the list.");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.print("\t");
            System.out.print(i+1 + ".");
            System.out.println(tasks.get(i));
        }
    }
    public static void markAsDone(int number) {
        try {
            tasks.get(number - 1).setDone(true);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + tasks.get(number - 1));
        } catch (NullPointerException e) {
            System.out.println("\tOops! task " + number + " does not exist");
        }
    }
    public static void markAsUndone(int number) {
        try {
            tasks.get(number - 1).setDone(false);
            System.out.println("\tNice! I've marked this task as not done yet:");
            System.out.println("\t" + tasks.get(number - 1));
        } catch (NullPointerException e) {
            System.out.println("\tOops! task " + number + " does not exist");
        }
    }


    public static void main(String[] args) {
        // Greetings
        String intro = "~~~~~~~~~~~~~~~~~~~\n"
                + "Hello! I'm Wenny!\n"
                + "How may I help you?\n"
                + "~~~~~~~~~~~~~~~~~~~";
        System.out.println(intro);
        DataManager dataManager = new DataManager("./data/data.txt");
        tasks = dataManager.loadData();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String[] substr = userInput.split("\\s+", 2);
            switch (substr[0]) {
            case "bye":
                printByeMessage();
                break;
            case "list":
                printTasks();
                break;
            case "mark":
                markAsDone(Integer.parseInt(substr[1]));
                dataManager.save(tasks);
                break;
            case "unmark":
                markAsUndone(Integer.parseInt(substr[1]));
                dataManager.save(tasks);
                break;
            case "todo":
                try {
                    addToTasks(substr[1], Type.TODO);
                    dataManager.save(tasks);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\tDescription of a todo cannot be empty");
                    break;
                }
            case "deadline":
                try {
                    addToTasks(substr[1], Type.DEADLINE);
                    dataManager.save(tasks);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\tDescription of a deadline cannot be empty");
                    break;
                }
            case "event":
                try {
                    addToTasks(substr[1], Type.EVENT);
                    dataManager.save(tasks);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\tDescription of a event cannot be empty");
                    break;
                }
            default:
                System.out.println("\tInvalid input, please try again");
            }
        }
    }
    public static void printByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.exit(0);
    }

}
