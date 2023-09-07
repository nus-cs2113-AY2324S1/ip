package Duke;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "       _       _        \n"
                + "      | |     | |       \n"
                + "      | | __ _| | _____ \n"
                + "  _   | |/ _` | |/ / _ \\\n"
                + " | |__| | (_| |   <  __/\n"
                + "  \\____/ \\__,_|_|\\_\\___|\n";
        // Bot Greet
        System.out.println(logo);
        System.out.println("Hello! I'm Jake");
        System.out.println("What can I do for you?");
        Scanner readIn = new Scanner(System.in);
        String userInput = "";
        ArrayList<Task> botMemory = new ArrayList<Task>();
        while (true) {
            System.out.print("User: ");
            userInput = readIn.nextLine();
            String parts[] = userInput.split(" ");
            if (parts[0].equalsIgnoreCase("bye")) {
                break;
            }
            if (parts[0].equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < botMemory.size(); i++) {
                    System.out.println(i + 1 + ".[" + botMemory.get(i).getStatusIcon() + "] " + botMemory.get(i).getDescription());
                  }
                continue;
            }
            if (parts[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                if (index > botMemory.size() - 1) {
                    System.out.println("ERROR! item " + (index + 1) + " does not exist");
                    continue;
                }
                botMemory.get(index).markDone();
                System.out.println("Nice! I've marked this task done:");
                System.out.println("[" + botMemory.get(index).getStatusIcon() + "] " + botMemory.get(index).getDescription());
                continue;
            }
            if (parts[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                if (index > botMemory.size() - 1) {
                    System.out.println("ERROR! item " + (index + 1) + " does not exist");
                    continue;
                }
                botMemory.get(index).unmarkDone();
                System.out.println("Ok, I've unmarked the following task:");
                System.out.println("[" + botMemory.get(index).getStatusIcon() + "] " + botMemory.get(index).getDescription());
                continue;
            }
            Task t = new Task(userInput);
            botMemory.add(t);
            System.out.println("added: " + userInput);
        }
        // Bot Exit 
        System.out.println("Bye. Hope to see you again soon!");
        readIn.close();
    }
}
