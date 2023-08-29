import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "  ____                  \n"
                + " |  _ \\  ___  _ __ ___  \n"
                + " | | | |/ _ \\| '_ ` _ \\ \n"
                + " | |_| | (_) | | | | | |\n"
                + " |____/ \\___/|_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Dom!");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String command = in.nextLine();
            System.out.println("____________________________________________________________");
            if(command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println(" " + getTaskList(list));
            } else {
                list.add(command);
                System.out.println("added: " + command);
            }
            //System.out.println(" " + command);
            System.out.println("____________________________________________________________");
            //if (command.equals("bye")) {
                //System.out.println(("Bye. Hope to see you again soon"));
                //break;
            }
        }
        private static String getTaskList(ArrayList<String>  list) {
            StringBuilder task = new StringBuilder();
            if(list.isEmpty()) {
                task.append("There is no task in your list");
            } else {
                for(int i = 0; i < list.size(); i++) {
                    task.append(i + 1).append(".").append(list.get(i)).append("\n");
                }
            }
            return task.toString();
        }
    }


