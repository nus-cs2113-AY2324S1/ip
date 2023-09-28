import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm");
        System.out.println(" ________  ________  ________  ________     \n" +
                "|\\   ____\\|\\   __  \\|\\   __  \\|\\   __  \\    \n" +
                "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \n" +
                " \\ \\  \\    \\ \\   __  \\ \\   _  _\\ \\   __  \\  \n" +
                "  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\n" +
                "                                            ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String line = input.nextLine();
        while (!line.equals("bye")) {

            System.out.println("____________________________________________________________");

            try {
                if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                } else if (line.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + tasks[taskIndex].toString());
                    }

                } else if (line.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskIndex].toString());
                    }
                } else {
                    String[] parts = line.split(" ", 2);
                    String command = parts[0];
                    String taskDescription = parts.length > 1 ? parts[1] : "";

                    switch (command) {
                    case "todo":
                        if (taskDescription.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks[taskCount] = new TodoTask(taskDescription);
                        break;
                    case "deadline":
                        tasks[taskCount] = new DeadlineTask(taskDescription);
                        break;
                    case "event":
                        tasks[taskCount] = new EventTask(taskDescription);
                        break;
                    default:
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    taskCount++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(tasks[taskCount - 1].toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                }
            }catch(DukeException e){
                System.out.println(e.getMessage());

            }

            System.out.println("____________________________________________________________\n");
            line = input.nextLine();
        }

        // When the user types "bye," exit the loop and display the goodbye message
        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
