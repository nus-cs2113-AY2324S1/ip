import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        String name = "Lexi";
        final String horizontal_line = "--------------------------------------------";

        System.out.println(logo);

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm " + name);
        System.out.println("How can I help you buddy?");
        System.out.println(horizontal_line);
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        List<Task> list = new ArrayList<Task>();
        do {
            System.out.println(horizontal_line);
            switch (input.toLowerCase().split(" ")[0]) {
            case "list":
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + "." + list.get(i).getStatus());
                }
                break;
            case "mark":
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index < 1 || index > list.size()) {
                    System.out.println("I am sorry, but this task does not exist");
                } else {
                    System.out.println("Great! I have marked this task as done:");
                    list.get(index-1).setDone(true);
                    System.out.println(list.get(index-1).getStatus());
                }
                break;
            case "unmark":
                index = Integer.parseInt(input.split(" ")[1]);
                if (index < 1 || index > list.size()) {
                    System.out.println("I am sorry, but this task does not exist");
                } else {
                    System.out.println("Alright, I have marked this task as not done:");
                    list.get(index - 1).setDone(false);
                    System.out.println(list.get(index - 1).getStatus());
                }
                break;
            default:
                System.out.println("Added: " + input);
                list.add(new Task(input));
                break;
            }
            System.out.println(horizontal_line);
            input = in.nextLine();
    } while (!input.toLowerCase().equals("bye"));

        System.out.println(horizontal_line);
        System.out.println("Have a wonderful day! Hope to see you again soon!");
        System.out.println(horizontal_line);

    }
}
