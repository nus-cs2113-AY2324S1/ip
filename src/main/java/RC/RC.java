package RC;

import RC.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class RC {
    public static void main(String[] args) {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            input = in.nextLine().trim();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    RCCommand.handleCommand(input, tasks);
                }
            } catch (RCException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
