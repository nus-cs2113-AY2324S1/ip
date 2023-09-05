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
        // Bot Read + Add + List
        Scanner readIn = new Scanner(System.in);
        String userInput = "";
        ArrayList<String> botMemory = new ArrayList<String>();
        while (true) {
            System.out.print("User: ");
            userInput = readIn.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < botMemory.size(); i++) {
                    System.out.println(i + 1 + ". " + botMemory.get(i));
                  }
                continue;
            }
            botMemory.add(userInput);
            System.out.println("added: " + userInput);
        }
        // Bot Exit 
        System.out.println("Bye. Hope to see you again soon!");
        readIn.close();
    }
}
