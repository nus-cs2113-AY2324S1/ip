package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import duke.TasksHandler;

public class Duke {
    public Duke() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm");
        System.out.println(" ________  ________  ________  ________     \n|\\   ____\\|\\   __  \\|\\   __  \\|\\   __  \\    \n\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \n \\ \\  \\    \\ \\   __  \\ \\   _  _\\ \\   __  \\  \n  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \n   \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\__\\ \\__\\\n    \\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\n                                            ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(); //array task list to store tasks

        // Load tasks from file
        try {
            TasksHandler.readFromFile(tasks);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        while(userInput.hasNextLine()){
            System.out.println("____________________________________________________________");
            String line = userInput.nextLine();

            try {
                if(line.equals("bye")){
                    // Save tasks to file before exiting the program
                    TasksHandler.writeToFile(tasks);
                    break;
                } else if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                } else if (line.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task task = tasks.get(taskIndex);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + task);
                    }
                } else if (line.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task task = tasks.get(taskIndex);
                        task.markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n" + task);
                    }
                } else if (line.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task removedTask = tasks.remove(taskIndex);
                        System.out.println("Noted. I've removed this task:\n" + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("Task not found. Please provide a valid task number.");
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
                        tasks.add(new TodoTask(taskDescription));
                        break;
                    case "deadline":
                        if (taskDescription.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        tasks.add(new DeadlineTask(taskDescription));
                        break;
                    case "event":
                        if (taskDescription.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        tasks.add(new EventTask(taskDescription));
                        break;
                    default:
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } catch (DukeException var10) {
                System.out.println(var10.getMessage());
            }

            System.out.println("____________________________________________________________\n");
        }

        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}