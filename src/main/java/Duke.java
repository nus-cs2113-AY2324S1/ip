import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from BotBuddy!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input;
        String[] inputArr;
        String command = "";
        String parameters = "";
        Task[] tasks = new Task[100];
        int noOfTasks = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine().trim();
            inputArr = input.split(" ", 2);
            command = inputArr[0];
            if (inputArr.length == 2) {
                parameters = inputArr[1];
            }

            switch (command) {
            case "list":
                if (noOfTasks == 0) {
                    System.out.println("____________________________________________________________");
                    System.out.println("There are currently no tasks!");
                    System.out.println("____________________________________________________________");
                    break;
                }
                // print out tasks
                System.out.println("____________________________________________________________");
                for (int i = 0; i < noOfTasks; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");
                break;

            case "mark":
                int taskToMark = Integer.parseInt(parameters) - 1;
                tasks[taskToMark].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("I've marked this task as done:");
                System.out.println(tasks[taskToMark].getStatusIcon() + " " + tasks[taskToMark].getDescription());
                System.out.println("____________________________________________________________");
                break;

            case "unmark":
                int taskToUnmark = Integer.parseInt(parameters) - 1;
                tasks[taskToUnmark].markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("I've unmarked this task:");
                System.out.println(tasks[taskToUnmark].getStatusIcon() + " " + tasks[taskToUnmark].getDescription());
                System.out.println("____________________________________________________________");
                break;

            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("Goodbye, hope to see you again soon!");
                System.out.println("____________________________________________________________");
                in.close();
                return;

            default:
                // add task
                tasks[noOfTasks] = new Task(input);
                noOfTasks++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + tasks[noOfTasks - 1].getDescription());
                System.out.println("____________________________________________________________");
                break;
            }

        }

    }
}
