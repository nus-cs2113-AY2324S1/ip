import java.util.Scanner;
public class Sun {
    public static void main(String[] args) {
        String logo = " ____               \n"
                    + "| ___| _   _  ______ \n"
                    + "| \\__ | | | || /--\\ |\n"
                    + " \\___|| |_| || |  | |\n"
                    + "/____/ \\__,_||_|  |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Sun!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String command;
        do{
            command = scanner.nextLine();

            if (!command.equals("bye")) {
                if (command.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i].getType()
                                +  tasks[i].getStatus() + " "+ tasks[i].description);
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(command.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[taskIndex].getType() +
                                tasks[taskIndex].getStatus() + " " + tasks[taskIndex].description);
                        System.out.println("____________________________________________________________");
                    }
                } else if (command.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(command.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[taskIndex].getType() +
                                tasks[taskIndex].getStatus() + " " + tasks[taskIndex].description);
                        System.out.println("____________________________________________________________");
                    }
                } else if (command.startsWith("todo ")){
                    Todo todoTask = new Todo(command);
                    tasks[taskCount] = todoTask;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todoTask.getType() +  todoTask.getStatus() + " "+ todoTask.description);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }else if (command.startsWith("deadline ")){
                    String deadlineCommand = command.substring(9);
                    String[] parts = deadlineCommand.split("/by");
                    Deadline deadlineTask = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks[taskCount] = deadlineTask;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadlineTask.getType() + deadlineTask.getStatus() + " " + deadlineTask.description +
                            " (by: " + deadlineTask.getDueDate() + ")");
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }else if (command.startsWith("event ")){
                    String deadlineCommand = command.substring(6);
                    String[] parts = deadlineCommand.split("/from|/to", 3);
                    Event eventTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks[taskCount] = eventTask;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(eventTask.getType() + eventTask.getStatus() + " " + eventTask.description +
                            " (from: " + eventTask.getStart() + " to: " + eventTask.getEnd() + ")");
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
            }
        } while (!command.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}

