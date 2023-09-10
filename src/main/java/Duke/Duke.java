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
        // Bot Greet Message
        System.out.println(logo);
        System.out.println("Hello! I'm Jake");
        System.out.println("What can I do for you?");
        Scanner readIn = new Scanner(System.in);
        String userInput = "";
        ArrayList<Task> botMemory = new ArrayList<Task>();

        while (true) {
            System.out.print("User: ");
            userInput = readIn.nextLine();
            String text[] = userInput.split(" ");

            // Bot Exit Message
            if (text[0].equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                readIn.close();
                break;
            }

            // Bot List feature = iterate botMemory then print everything
            if (text[0].equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < botMemory.size(); i++) {
                    System.out.println(i + 1 + ". " + botMemory.get(i).getDescription());
                }
            }

            // Bot Mark task feature = set task status to done with markTask();
            if (text[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(text[1]) - 1;
                botMemory.get(index).markTask();
                System.out.println("Nice! I've marked this task done:");
                System.out.println(botMemory.get(index).getDescription());
            }

            // Bot Unmark task feature = unset task status with unMarkTask();
            if (text[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(text[1]) - 1;
                botMemory.get(index).unMarkTask();
                System.out.println("Ok, I've unmarked the following task:");
                System.out.println(botMemory.get(index).getDescription());
            }

            // Bot Todo task feature (use: todo [desc])
            if (text[0].equalsIgnoreCase("todo")) {
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex);
                Task t = new Todo(description);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }

            // Bot Event task feature (use: event [desc] /from [start] /to [end])
            if (text[0].equalsIgnoreCase("event")) {
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
            if (text[0].equalsIgnoreCase("deadline")) {
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
        }
    }
}
