package Duke;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        final String LOGO = "       _       _        \n"
                + "      | |     | |       \n"
                + "      | | __ _| | _____ \n"
                + "  _   | |/ _` | |/ / _ \\\n"
                + " | |__| | (_| |   <  __/\n"
                + "  \\____/ \\__,_|_|\\_\\___|\n";

        // Bot Greet Message
        System.out.println(LOGO);
        System.out.println("Hello! I'm Jake");
        System.out.println("What can I do for you?");
        Scanner inputReader = new Scanner(System.in);
        String userInput = "";
        ArrayList<Task> botMemory = new ArrayList<Task>();

        while (true) {
            System.out.print("User: ");
            userInput = inputReader.nextLine();
            String text[] = userInput.split(" ");

            try {
                validateInput(userInput); 
            } catch (DukeException e) { 
                System.out.println("DukeException: " + e.getMessage()); 
                continue; 
            }

            // Bot Exit Message
            if (text[0].equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                inputReader.close();
                break;
            }

            // Bot List feature = iterate botMemory then print everything
            else if (text[0].equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < botMemory.size(); i++) {
                    System.out.println(i + 1 + ". " + botMemory.get(i).getDescription());
                }
            }

            // Bot Mark task feature = set task status to done with markTask();
            else if (text[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(text[1]) - 1;
                if (index > botMemory.size() - 1 || (index + 1) < 1) { 
                    System.out.println("I'm sorry, but item " + (index + 1) + " doesn't exist");
                    continue;
                }
                botMemory.get(index).markTask();
                System.out.println("Nice! I've marked this task done:");
                System.out.println(botMemory.get(index).getDescription());
            }

            // Bot Unmark task feature = unset task status with unMarkTask();
            else if (text[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(text[1]) - 1;
                if (index > botMemory.size() - 1 || (index + 1) < 1) { 
                    System.out.println("I'm sorry, but item " + (index + 1) + " doesn't exist");
                    continue;
                }
                botMemory.get(index).unMarkTask();
                System.out.println("Ok, I've unmarked the following task:");
                System.out.println(botMemory.get(index).getDescription());
            }

            // Bot Todo task feature (use: todo [desc])
            else if (text[0].equalsIgnoreCase("todo")) {
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex);
                Task t = new Todo(description);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }

            // Bot Event task feature (use: event [desc] /from [start] /to [end])
            else if (text[0].equalsIgnoreCase("event")) {
                int startIndex = userInput.indexOf("/from ");
                int endIndex = userInput.indexOf("/to ");
                String startTime = userInput.substring(startIndex + 6, endIndex);
                String endTime = userInput.substring(endIndex + 4);
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex, startIndex);
                Task t = new Event(description, startTime, endTime);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }

            // Bot Deadline task feature (use: deadline [desc] /by [when])
            else if (text[0].equalsIgnoreCase("deadline")) {
                int whenIndex = userInput.indexOf("/by");
                String when = userInput.substring(whenIndex + 4);
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex, whenIndex);
                Task t = new Deadline(description, when);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }
            
            // Bot default reaction to unknown action
            else {
                System.out.println("I'm sorry, but I don't know what that means :[");
            }
        }
    }

    // Method to validate user input and throw DukeException if it doesn't match requirements
    public static void validateInput(String userInput) throws DukeException {
        String text[] = userInput.split(" ");
        if (text[0].equalsIgnoreCase("todo") && text.length < 2) {
            throw new DukeException("☹ The 'todo' requires a description (use: todo [desc])");
        } else if (text[0].equalsIgnoreCase("event") && (text.length < 5 || !userInput.contains("/from") || !userInput.contains("/to"))) {
            throw new DukeException("☹ The 'event' requires a description, start time, and end time (use: event [desc] /from [start] /to [end])");
        } else if (text[0].equalsIgnoreCase("deadline") && (text.length < 4 || !userInput.contains("/by"))) {
            throw new DukeException("☹ The 'deadline' requires a description and due date (use: deadline [desc] /by [when])");
        } else if (text[0].equalsIgnoreCase("mark") && (text.length != 2 || !isInteger(text[1]))) {
            throw new DukeException("☹ The 'mark' requires an integer to represent an existing task (use: mark [task no.])");
        } else if (text[0].equalsIgnoreCase("unmark") && (text.length != 2 || !isInteger(text[1]))) {
            throw new DukeException("☹ The 'unmark' requires an integer to represent an existing task (use: unmark [task no.])");
        }
    }

    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
