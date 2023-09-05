//package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /*
     * This method prints the greetings message when the user starts the program.
     */
    public static void greet() {
        String logo = "  ____                  \n"
                + " |  _ \\  ___  _ __ ___  \n"
                + " | | | |/ _ \\| '_ ` _ \\ \n"
                + " | |_| | (_) | | | | | |\n"
                + " |____/ \\___/|_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        String greetings = "____________________________________________________________" + "\n" +
                "Hello! I'm Dom" + "\n" +
                "What can I do for you?" + "\n" +
                "____________________________________________________________";
        System.out.println(greetings);
    }

    /*
     * This method prints the goodbye message when the user exits the program.
     */
    public static void goodbye() {
        String goodbye = "____________________________________________________________" + "\n" +
                "Bye. Hope to see you again soon!" + "\n" +
                "____________________________________________________________";
        System.out.println(goodbye);
    }

    /*
     * This method prints the input message.
     */
    public static void echo(String input) {
        System.out.println(input);
    }

    /*
     * This method prints the list of tasks.
     */
    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks.");
        } else {
            System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskType = task instanceof Todo ? "[T]" : (task instanceof Deadline ? "[D]" : "[E]");
            String statusIcon = task.getStatusIcon();
            String description = task.getDescription();
            
            if (task instanceof Deadline) {
                description += " (by: " + ((Deadline) task).getBy() + ")";
            } else if (task instanceof Event) {
                description += " (from: " + ((Event) task).getFrom() + " to: " + ((Event) task).getTo() + ")";
            }
            
            System.out.println(" " + (i + 1) + "." + taskType + statusIcon + " " + description);
        }
    }
}
    public static void main(String[] args) {
        greet();

        try (Scanner givenTask = new Scanner(System.in)) {  
            ArrayList<Task> tasks = new ArrayList<Task>();

            while (true) {
                String command = givenTask.nextLine();
                System.out.println("____________________________________________________________");

                if (command.equalsIgnoreCase("bye")) {
                    goodbye();
                    break;
                } else if (command.equalsIgnoreCase("list")) { // if user inputs "list", UI prints the list of tasks that is stored in the ArrayList    
                    listTasks(tasks);
                
                // split the command into two parts, "mark" and the task number
                // e.g. "mark 1" will be split into "mark" and "1"
                // the task number is then parsed into an integer
                // the task number is then used to get the task from the ArrayList
                // the task is then marked as done
                // the UI prints the task that is marked as done
                // if the task number is invalid, the UI prints an error message
                } else if (command.startsWith("mark")) { // if user inputs "mark", UI marks the task as done
                    String[] parts = command.split(" ");
                    if (parts.length == 2) {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            Task task = tasks.get(taskIndex);
                            task.markAsDone(true);
                            System.out.println(" Nice! I've marked this task as done:\n" + "   " + task.getStatusIcon()
                                    + " " + task.getDescription());
                        } else {
                            System.out.println(" Invalid task number.");
                        }
                    }
                // split the command into two parts, "unmark" and the task number
                // e.g. "unmark 1" will be split into "unmark" and "1"
                // the task number is then parsed into an integer
                // the task number is then used to get the task from the ArrayList
                // the task is then marked as not done yet
                // the UI prints the task that is marked as not done yet
                // if the task number is invalid, the UI prints an error message
                } else if (command.startsWith("unmark")) {
                    String[] parts = command.split(" ");
                    if (parts.length == 2) {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            Task task = tasks.get(taskIndex);
                            task.markAsUndone(true);
                            System.out.println("OK, I've marked this task as not done yet:\n" + " "
                                    + task.getStatusIcon() + " " + task.getDescription());
                        } else {
                            System.out.println(" Invalid task number.");
                        }
                    }
                } else if (command.startsWith("todo ")) {
                    // Handle adding a ToDo task
                    String description = command.substring(5); // Remove "todo "
                    tasks.add(new Todo(description));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (command.startsWith("deadline ")) {
                    String[] parts = command.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        // Handle adding a Deadline task
                        String description = parts[0];
                        String by = parts[1];
                        tasks.add(new Deadline(description, by));
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println(" Invalid command.");
                    }
                } else if (command.startsWith("event ")) {
                    // Handle adding an Event task
                    String[] parts = command.substring(6).split(" /from ");
                    if (parts.length == 2) {
                        String description = parts[0];
                        String[] timeParts = parts[1].split(" /to ");
                        String from = timeParts[0];
                        String to = timeParts[1];
                        tasks.add(new Event(description, from, to));
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println(" Invalid event command format.");
                    }
                } else {
                    tasks.add(new Task(command));
                    echo(" added: " + command);
                }
                System.out.println("____________________________________________________________");
            }
        }
    }
}
