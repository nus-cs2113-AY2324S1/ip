package chattie;

import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Chat {

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    static void startChat() {
        int count = 0;
        ArrayList<Task> list = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("\tHello! I'm Chattie! How was your day?");
        printLine();

        line = in.nextLine();
        greetUser(line);

        line = in.nextLine();
        while(!line.equals("bye")) {
            printLine();
            count = readCommands(line, list, count);
            printLine();
            line = in.nextLine();
        }

        printLine();
        System.out.println("\tByeeeee. Hope to see you again soon! :)");
        printLine();
    }

    public static void greetUser(String line) {
        line = line.toLowerCase();

        printLine();
        if(line.contains("good")) {
            System.out.println("\tGreat to hear that! :D");
        } else if (line.contains("bad")) {
            System.out.println("\tI'm sorry to hear that :(");
        } else {
            System.out.println("\tSounds like you had quite a day");
        }
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    private static int readCommands(String line, ArrayList<Task> list, int count) {
        try {
            if(line.equals("list")) {
                TaskCommands.listTasks(list, count);
            } else if(line.startsWith("mark") || line.startsWith("unmark")) {
                String[] command = line.split(" ");
                TaskCommands.markTask(list, command);
            } else if (line.startsWith("todo")) {
                count = TaskCommands.addTodo(list, line, count);
            } else if (line.startsWith("deadline")) {
                count = TaskCommands.addDeadline(list, line, count);
            } else if (line.startsWith("event")) {
                count = TaskCommands.addEvent(list, line, count);
            } else if (line.startsWith("delete")) {
                String[] command = line.split(" ");
                TaskCommands.deleteTask(list, command);
                count--;
            } else {
                throw new ChattieException(ErrorType.INVALID_COMMAND);
            }
        } catch (ChattieException e) {
            System.out.println("\tPlease try again");
        }
        return count;
    }
}
