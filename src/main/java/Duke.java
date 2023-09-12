import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            case "todo":
                // add todo
                tasks[noOfTasks] = new Todo(parameters);
                System.out.println("____________________________________________________________");
                System.out.println("Got it, I've added this task:");
                System.out.println(tasks[noOfTasks]);
                System.out.println("____________________________________________________________");
                noOfTasks++;
                break;

            case "event":
                // add event
                String[] eventDetails = parameters.split("/from");
                String eventName = eventDetails[0].trim();
                eventDetails = eventDetails[1].split("/to");
                String eventFrom = eventDetails[0].trim();
                String eventTo = eventDetails[1].trim();
                tasks[noOfTasks] = new Event(eventName, eventFrom, eventTo);
                System.out.println("____________________________________________________________");
                System.out.println("Got it, I've added this task:");
                System.out.println(tasks[noOfTasks]);
                System.out.println("____________________________________________________________");
                noOfTasks++;
                break;

            case "deadline":
                // add deadline
                String[] deadlineDetails = parameters.split("/by");
                String deadlineName = deadlineDetails[0].trim();
                String deadlineBy = deadlineDetails[1].trim();
                tasks[noOfTasks] = new Deadline(deadlineName, deadlineBy);
                System.out.println("____________________________________________________________");
                System.out.println("Got it, I've added this task:");
                System.out.println(tasks[noOfTasks]);
                System.out.println("____________________________________________________________");
                noOfTasks++;
                break;

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
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
                break;

            case "mark":
                int taskToMark = Integer.parseInt(parameters) - 1;
                tasks[taskToMark].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("I've marked this task as done:");
                System.out.println(tasks[taskToMark]);
                System.out.println("____________________________________________________________");
                break;

            case "unmark":
                int taskToUnmark = Integer.parseInt(parameters) - 1;
                tasks[taskToUnmark].markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("I've unmarked this task:");
                System.out.println(tasks[taskToUnmark]);
                System.out.println("____________________________________________________________");
                break;

            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("Goodbye, hope to see you again soon!");
                System.out.println("____________________________________________________________");
                in.close();
                return;

            default:
                System.out.println("____________________________________________________________");
                System.out.println("Invalid command!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}
