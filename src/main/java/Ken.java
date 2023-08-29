import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ken {
    public static void main(String[] args) {
        String logo = " _   __ ______  __    __\n"
                    + "| | / /|  ____||  \\  |  |\n"
                    + "| |/ / | |____ |   \\ |  |\n"
                    + "|   /  |  ____||    \\|  |\n"
                    + "| |\\ \\ | |____ |        |\n"
                    + "|_| \\_\\|______||__|\\____|";
        String line = "_______________________________________________\n";
        System.out.println(logo);

        System.out.println(line
                + "Hello! I'm Ken!\n"
                + "What can I do for you?\n"
                + line);

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;
        while (true) {
            String[] input = scan.nextLine().split(" ");

            switch(input[0]) {
            case "list":
                System.out.println(line + "Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) +"." + tasks.get(i).toString());
                }
                System.out.println(line);
                break;
            case "mark":
                int markNumber = Integer.parseInt(input[1]) - 1;
                tasks.get(markNumber).markAsDone();
                System.out.println(line + "Nice! I've marked this task as done:\n"
                        + tasks.get(markNumber).toString() + "\n" + line);
                break;
            case "unmark":
                int unmarkNumber = Integer.parseInt(input[1]) - 1;
                tasks.get(unmarkNumber).unmarkAsDone();
                System.out.println(line + "Ok, I've marked this task as not done yet:\n"
                        + tasks.get(unmarkNumber).toString() + "\n" + line);
                break;
            case "bye":
                System.out.println(line
                        + "Bye. Hope to see you again soon!\n"
                        + line);
                return;
            default:
                String taskName = String.join(" ", input);
                Task task = new Task(taskName);
                tasks.add(task);
                System.out.println(line + "added: " + taskName + "\n" + line);
                break;
            }

        }

    }
}
