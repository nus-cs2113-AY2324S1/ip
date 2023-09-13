import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import tasks.DukeException;

public class Duke {
    public static final String lineDivider = "______________________________________________________________________";

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
        String greetings = lineDivider +
                "\nHello! I'm Dom\n" +
                "What can I do for you?\n" +
                lineDivider;
        System.out.println(greetings);
    }

    /*
     * This method prints the goodbye message when the user exits the program.
     */
    public static void goodbye() {
        String goodbye = lineDivider +
                "\nBye. Hope to see you again soon!\n" +
                lineDivider;
        System.out.println(goodbye);
    }

    public static void getHelp() {
        String help = "\nHere are the list of commands that you can use:\n" +
                "bye - exits the program\n" +
                "list - lists all the tasks\n" +
                "todo <description> - adds a ToDo task\n" +
                "deadline <description> /by <time> - adds a Deadline task\n" +
                "event <description> /from <time> /to <time> - adds an Event task\n" +
                "mark <task number> - marks the task as done\n" +
                "unmark <task number> - marks the task as not done yet\n";

        System.out.println(help);
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
            System.out.println("Error: No tasks is available.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);

                /*
                 * checks if the task is a ToDo, Deadline or Event
                 * prints the task type, status icon and description
                 */
                String taskType = task.getClass().equals(Todo.class) ? "[T]"
                        : (task.getClass().equals(Deadline.class) ? "[D]" : "[E]");
                String statusIcon = task.getStatusIcon();
                String description = task.getDescription();

                if (task.getClass().equals(Deadline.class)) {
                    description += " (by: " + ((Deadline) task).getBy() + ")";
                } else if (task.getClass().equals(Event.class)) {
                    description += " (from: " + ((Event) task).getFrom() + " to: " + ((Event) task).getTo() + ")";
                }
                System.out.println(" " + (i + 1) + "." + taskType + statusIcon + " " + description);
            }
        }
    }

    public static void main(String[] args) {
        greet();

        try (Scanner givenTask = new Scanner(System.in)) {
            ArrayList<Task> tasks = new ArrayList<>();

            while (true) {
                String command = givenTask.nextLine();
                System.out.println(lineDivider);

                try {
                    if (command.equalsIgnoreCase("bye")) {
                        goodbye();
                        break;
                    } else if (command.equalsIgnoreCase("list")) {
                        // if user inputs "list", UI prints the list of tasks that is stored in the
                        // ArrayList
                        listTasks(tasks);

                        /**
                         * split the command into two parts, "mark" and the task number
                         * e.g. "mark 1" will be split into "mark" and "1"
                         * the task number is then parsed into an integer
                         * the task number is then used to get the task from the ArrayList
                         * the task is then marked as done
                         * the UI prints the task that is marked as done
                         * if the task number is invalid, the UI prints an error message
                         */

                    } else if (command.startsWith("mark")) {
                        if (command.substring(5).isEmpty()) {
                            System.out.println("Error: Task number cannot be empty\n");
                            System.out.println("Please enter command in the format: mark <task number>");
                        }
                        try {
                            String[] parts = command.split(" ");
                            if (parts.length == 2) {
                                int taskIndex = Integer.parseInt(parts[1]) - 1;
                                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                                    Task task = tasks.get(taskIndex);
                                    task.markAsDone(true);
                                    System.out.println(
                                            " Nice! I've marked this task as done:\n" + "   " + task.getStatusIcon()
                                                    + " " + task.getDescription());
                                } else {
                                    System.out.println(" Invalid task number.");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid task number");
                        }
                        /**
                         * split the command into two parts, "unmark" and the task number
                         * e.g. "unmark 1" will be split into "unmark" and "1"
                         * the task number is then parsed into an integer
                         * the task number is then used to get the task from the ArrayList
                         * the task is then marked as not done yet
                         * the UI prints the task that is marked as not done yet
                         * if the task number is invalid, the UI prints an error message
                         */
                    } else if (command.startsWith("unmark")) {
                        if (command.substring(7).isEmpty()) {
                            System.out.println("Error: Task number cannot be empty\n");
                            System.out.println("Please enter command in the format: unmark <task number>");
                        }
                        try {
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
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid task number");
                        }

                        /**
                         * Handle adding a ToDo task
                         * e.g. "todo read book" will be split into "todo" and "read book"
                         * the description is then parsed into a ToDo task.
                         * the ToDo task is then added to the ArrayList
                         * the UI prints the ToDo task that is added
                         * the UI prints the number of tasks in the ArrayList
                         * if the description is empty, the UI prints an error message
                         */
                    } else if (command.startsWith("todo ")) {
                        try {
                            if (command.substring(5).isEmpty()) {
                                throw new DukeException();
                            }
                            tasks.add(new Todo(command.substring(5)));
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + tasks.get(tasks.size() - 1));
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        } catch (DukeException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.\n");
                            System.out.println("please enter command in the format: todo <description>");
                        }
                        /**
                         * Handle adding a Deadline task
                         * e.g. "deadline return book /by 2pm" will be split into "deadline" and "return
                         * book /by 2pm"
                         * the description and by time is then parsed into a Deadline task.
                         * the Deadline task is then added to the ArrayList
                         * the UI prints the Deadline task that is added
                         * the UI prints the number of tasks in the ArrayList
                         * if the description or by time is empty, the UI prints an error message
                         * if the by time is not in the correct format, the UI prints an error message
                         */
                    } else if (command.startsWith("deadline ")) {
                        try {
                            if (command.substring(9).isEmpty()) {
                                throw new DukeException();
                            }
                            String[] parts = command.substring(9).split(" /by ");
                            if (parts.length == 2) {
                                String description = parts[0];
                                String by = parts[1].trim();
                                if (by.isEmpty()) {
                                    throw new DukeException();
                                }
                                tasks.add(new Deadline(description, by));
                                System.out.println(" Got it. I've added this task:");
                                System.out.println("   " + tasks.get(tasks.size() - 1));
                                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            } else {
                                System.out.println(" Invalid command.");
                            }
                        } catch (DukeException e) {
                            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.\n");
                            System.out.println("please enter command in the format: deadline <description> /by <time>");
                        }
                        /**
                         * Handle adding an Event task
                         * e.g. "event project meeting /from 2pm /to 4pm" will be split into "event" and
                         * "project meeting /from 2pm /to 4pm"
                         * the description, from time and to time is then parsed into an Event task.
                         * the Event task is then added to the ArrayList
                         * the UI prints the Event task that is added
                         * the UI prints the number of tasks in the ArrayList
                         * if the description, from time or to time is empty, the UI prints an error
                         * message
                         * if the from time or to time is not in the correct format, the UI prints an
                         * error message
                         * if the from time is after the to time, the UI prints an error message
                         */
                    } else if (command.startsWith("event ")) {
                        try {
                            String descriptionAndTime = command.substring(6).trim(); // Trim to handle leading/trailing spaces
                    
                            if (descriptionAndTime.isEmpty()) {
                                throw new DukeException();
                            }
                    
                            String[] parts = descriptionAndTime.split(" /from ");
                    
                            if (parts.length == 2) {
                                String description = parts[0];
                                String[] timeParts = parts[1].split(" /to ");
                    
                                if (timeParts.length == 2) {
                                    String from = timeParts[0].trim(); // Trim to handle leading/trailing spaces
                                    String to = timeParts[1].trim(); // Trim to handle leading/trailing spaces
                    
                                    //if (from.isEmpty() || to.isEmpty()) {
                                        //throw new DukeException();
                                    //}
                    
                                    tasks.add(new Event(description, from, to));
                                    System.out.println(" Got it. I've added this task:");
                                    System.out.println("   " + tasks.get(tasks.size() - 1));
                                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                                } else {
                                    throw new DukeException();
                                }
                            } else {
                                System.out.println(" Invalid event command format.");
                            }
                        } catch (DukeException e) {
                            System.out.println("☹ OOPS!!! The description and /from or /to time of an event cannot be empty.\n");
                            System.out.println("Please enter the command in the format: event <description> /from <time> /to <time>");
                        }
                    } else if (command.startsWith("help")) {
                        getHelp();
                    } else {
                        throw new DukeException();
                    }
                } catch (DukeException e) {
                    System.out.println("Error: ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    System.out.println("If assistance is required, please type 'help' for more information.");
                }
                System.out.println(lineDivider);
            }
        }
    }
}