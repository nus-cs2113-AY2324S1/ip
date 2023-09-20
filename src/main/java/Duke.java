import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import tasks.DukeException;

public class Duke {
    public static final String lineDivider = "______________________________________________________________________";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";

    private static final String HELP_MESSAGE = "\nHere are the list of commands that you can use:\n"
            + COMMAND_EXIT + " - exits the program\n"
            + COMMAND_LIST + " - lists all the tasks\n"
            + COMMAND_TODO + " <description> - adds a ToDo task\n"
            + COMMAND_DEADLINE + " <description> /by <time> - adds a Deadline task\n"
            + COMMAND_EVENT + " <description> /from <time> /to <time> - adds an Event task\n"
            + COMMAND_MARK + " <task number> - marks the task as done\n"
            + COMMAND_UNMARK + " <task number> - marks the task as not done yet\n"
            + COMMAND_DELETE + " <task number> - deletes the task\n";

    /*
     * This method creates a file if it does not exist.
     * @param f the file to be created
     * @return true if the file is created successfully, false otherwise
     * @throws IOException if the file cannot be created
     */
    private static boolean createFile(File f) {
        try {
            return f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: ☹ OOPS!!! Unable to create file.");
            return false;
        }
    }

    /*
     * This method creates a data directory if it does not exist.
     * @param f the file to be created
     * @return true if the file is created successfully, false otherwise
     */
    private static void makeDataDir() {
        File dataDirectory = new File("./data/");
        if(!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
    }

    /*
     * This method appends the task to the file.
     * @param filePath the path of the file
     * @param task the task to be appended to the file
     * @throws IOException if the file cannot be written to
     */
    private static void appendToFile(String filePath, Task task) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.toData() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: ☹ OOPS!!! Unable to write to file.");
        }
    }

    /*
     * This method loads the data from the file.
     * @param file the file to be loaded
     * @param list the list of tasks
     * @return the list of tasks
     * @throws FileNotFoundException if the file cannot be found
     */
    private static ArrayList<Task> loadData(File file, ArrayList<Task> list) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskData = s.nextLine();
                String taskType = taskData.substring(0, 1);

                switch(taskType) {
                case "T":
                    list.add(Todo.dataToTask(taskData.substring(4)));
                    break;
                
                case "D":
                    list.add(Deadline.dataToTask(taskData.substring(4)));
                    break;
                
                case "E":
                    list.add(Event.dataToTask(taskData.substring(4)));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: ☹ OOPS!!! File not found. Unable to load data.");
        }
        return list;
    }


    private static void writeAllToFile(ArrayList<Task> list, File f) {
        try {
            FileWriter fw = new FileWriter(f);
            for(int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            
            System.out.println("Error: ☹ OOPS!!! Unable to write to file.");
        }
    }
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
        echo("bye");
        System.out.println("\nBye. Hope to see you again soon!");
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
            System.out.println("ERROR: ☹ OOPS!!! No tasks is available.");
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

    /*
     * This method marks the task as done.
     *
     * splits the command into two parts: command type and task number
     * e.g. mark 1 will be split into "mark" and "1"
     * the task number is then parsed into an integer
     * the task number is then used to get the task from the list of tasks
     * If the task number is invalid, it will print an error message.
     * If the task number is valid, it will print the task that is marked as done.
     *
     * @param tasks the list of tasks
     * @param parts the command that is split into two parts
     * @throws DukeException if the task number is empty or not an integer
     * @throws NumberFormatException if the task number is not an integer
     */
    private static void markTask(ArrayList<Task> tasks, String[] parts) {
        try {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException();
            }
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task task = tasks.get(taskIndex);
                task.markAsDone(true);
                System.out.println(" Nice! I've marked this task as done:\n" + "   " + task.getStatusIcon()
                        + " " + task.getDescription());
            } else {
                System.out.println("ERROR: ☹ OOPS!!! Invalid task number.");
            }
        } catch (DukeException e) {
            System.out.println("Error: ☹ OOPS!!! Task number cannot be empty\n");
            System.out.println("Please enter command in the format: mark <task number>");
        } catch (NumberFormatException e) {
            System.out.println("Error: ☹ OOPS!!! Task number must be an integer.\n");
            System.out.println("Please enter command in the format: mark <task number>");
        }
    }

    /*
     * This method marks the task as not done yet.
     *
     * splits the command into two parts: command type and task number
     * e.g. unmark 1 will be split into "unmark" and "1"
     * the task number is then parsed into an integer
     * the task number is then used to get the task from the list of tasks
     * If the task number is invalid, it will print an error message.
     * If the task number is valid, it will print the task that is marked as not done yet.
     *
     * @param tasks the list of tasks
     * @param parts the command that is split into two parts
     * @throws DukeException if the task number is empty or not an integer
     * @throws NumberFormatException if the task number is not an integer
     */
    private static void unmarkTask(ArrayList<Task> tasks, String[] parts) {
        try {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException();
            }

            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task task = tasks.get(taskIndex);
                task.markAsUndone(true);
                System.out.println("OK, I've marked this task as not done yet:\n" + " "
                        + task.getStatusIcon() + " " + task.getDescription());
            } else {
                System.out.println("Error: Invalid task number.");
            }
        } catch (DukeException e) {
            System.out.println("Error: ☹ OOPS!!! Task number cannot be empty\n");
            System.out.println("Please enter command in the format: unmark <task number>");
        } catch (NumberFormatException e) {
            System.out.println("Error: ☹ OOPS!!! Task number must be an integer.\n");
            System.out.println("Please enter command in the format: unmark <task number>");
        }
    }


    /*
     * This method adds a ToDo task.
     * splits the command into two parts: command type and description
     * e.g. todo read book will be split into "todo" and "read book"
     * the description is then used to create a new ToDo task
     * the new ToDo task is then added to the list of tasks
     * If the description is empty, it will print an error message.
     * If the description is not empty, it will print the new ToDo task.
     *
     * @param tasks the list of tasks
     * @param parts the command that is split into two parts
     * @throws DukeException if the description is empty
     * @throws NumberFormatException if the task number is not an integer
     *
     */
    private static void addTodo(ArrayList<Task> tasks, String[] parts) {
        try {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException();
            }
            tasks.add(new Todo(parts[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.\n");
            System.out.println("please enter command in the format: todo <description>");
        }
    }

    /*
     * This method adds a Deadline task.
     * splits the command into two parts: command type and description
     * e.g. deadline return book /by 2pm will be split into "deadline" and "return book /by 2pm"
     * the description is then used to create a new Deadline task
     * the new Deadline task is then added to the list of tasks
     * If the description is empty, it will print an error message.
     * If the description is not empty, it will print the new Deadline task.
     * If the description is not empty, but the time is empty, it will print an error message.
     * If the description is not empty, but the time is not empty, it will print the new Deadline task.
     * If the description is not empty, but the time is not empty, but the time is invalid, it will print an error message.
     * If the description is not empty, but the time is not empty, but the time is valid, it will print the new Deadline task.
     *
     * @param tasks the list of tasks
     * @param parts the command that is split into two parts
     * @throws DukeException if the description is empty
     *
     *
     */
    private static void addDeadline(ArrayList<Task> tasks, String[] parts) {
        try {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException();
            }
            String[] deadlineParts = parts[1].split(" /by ");
            if (deadlineParts.length == 2) {
                String description = deadlineParts[0];
                String by = deadlineParts[1].trim();
                tasks.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Invalid command.");
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.\n");
            System.out.println("please enter command in the format: deadline <description> /by <time>");
        }
    }

    /*
     * This method adds an Event task.
     * splits the command into two parts: command type and description
     * e.g. event project meeting /from 2pm /to 4pm will be split into "event" and "project meeting /from 2pm /to 4pm"
     * the description is then used to create a new Event task
     * the new Event task is then added to the list of tasks
     * If the description is empty, it will print an error message.
     * If the description is not empty, it will print the new Event task.
     * If the description is not empty, but the time is empty, it will print an error message.
     * If the description is not empty, but the time is not empty, it will print the new Event task.
     * If the description is not empty, but the time is not empty, but the time is invalid, it will print an error message.
     * If the description is not empty, but the time is not empty, but the time is valid, it will print the new Event task.
     *
     * @param tasks the list of tasks
     * @param parts the command that is split into two parts
     * @throws DukeException if the description is empty
     *
     */
    private static void addEvent(ArrayList<Task> tasks, String[] parts) {
        try {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException();
            }
            String[] eventParts = parts[1].split(" /from ");
            if (eventParts.length == 2) {
                String description = eventParts[0];
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length == 2) {
                    String from = timeParts[0].trim();
                    String to = timeParts[1].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Invalid command.");
                }
            } else {
                System.out.println("Invalid command.");
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.\n");
            System.out.println("please enter command in the format: event <description> /from <time> /to <time>");
        }
    }
    /*
     * This method deletes a task.
     *
     * splits the command into two parts: command type and task number
     * e.g. delete 1 will be split into "delete" and "1"
     * the task number is then parsed into an integer
     * If the task number is invalid, it will print an error message.
     * if the task number is valid, it will print the task that is deleted.
     *
     * @param tasks the list of tasks
     * @param parts the command that is split into two parts
     * @throws DukeException if the task number is empty or not an integer
     * @throws NumberFormatException if the task number is not an integer
     *
     */
    private static void deleteTask(ArrayList<Task> tasks, String[] parts) {
        try {
            if(parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException();
            }
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removeTask = tasks.remove(taskIndex);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removeTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("ERROR: ☹ OOPS!!! Invalid task number.");
            }
        } catch (DukeException e) {
            System.out.println("Error: ☹ OOPS!!! Task number cannot be empty\n");
            System.out.println("Please enter command in the format: delete <task number>");
        } catch (NumberFormatException e) {
            System.out.println("Error: ☹ OOPS!!! Task number must be an integer.\n");
            System.out.println("Please enter command in the format: delete <task number>");
        }
    }

    public static void main(String[] args) {
        greet();

        try (Scanner givenTask = new Scanner(System.in)) {
            ArrayList<Task> tasks = new ArrayList<>();
            String filePath = "data/duke.txt";
            makeDataDir();
            File f = new File(filePath);
            if(!createFile(f)){
                tasks = loadData(f, tasks);
            }


            while(true) {
                String command = givenTask.nextLine().trim();
                System.out.println(lineDivider);

                String[] parts = command.split(" ", 2);
                String commandType = parts.length > 0 ? parts[0] : "";

                try{
                    switch(commandType.toLowerCase()){
                        case "bye":
                            goodbye();
                            break;

                        case "list":
                            listTasks(tasks);
                            break;

                        case "help":
                            System.out.println(HELP_MESSAGE);
                            break;

                        case "mark":
                            markTask(tasks, parts);
                            writeAllToFile(tasks, f);
                            break;

                        case "unmark":
                            unmarkTask(tasks, parts);
                            writeAllToFile(tasks, f);
                            break;

                        case "todo":
                            addTodo(tasks, parts);
                            appendToFile(filePath, tasks.get(tasks.size() - 1));
                            break;

                        case "deadline":
                            addDeadline(tasks, parts);
                            appendToFile(filePath, tasks.get(tasks.size() - 1));
                            break;

                        case "event":
                            addEvent(tasks, parts);
                            appendToFile(filePath, tasks.get(tasks.size() - 1));
                            break;

                        case "delete":
                            deleteTask(tasks, parts);
                            break;

                        default:
                            echo(command);
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