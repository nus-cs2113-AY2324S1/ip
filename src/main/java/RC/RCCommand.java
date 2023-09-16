package RC;

import RC.task.Deadline;
import RC.task.Event;
import RC.task.Task;
import RC.task.Todo;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class RCCommand {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EXIT_COMMAND = "bye";
    private static final String BY_COMMAND = "/by";
    private static final String FROM_COMMAND = "/from";
    private static final String TO_COMMAND = "/to";
    private static final String FILE_PATH = "data/tasks.txt";
    private static boolean isExit = false;

    private static void addTodo(String input, ArrayList<Task> tasks) throws RCException {
        if (input.isEmpty()) {
            String errorMessage = "\tOOPS!!! The description of a todo cannot be empty.";
            throw new RCException(errorMessage);
        }

        tasks.add(new Todo(input));
        tasks.get(tasks.size() - 1).printAddedTask();
        //writeToFile(FILE_PATH, tasks);
    }

    private static void addEvent(String input, ArrayList<Task> tasks) throws RCException {
        int fromIndex = input.indexOf(FROM_COMMAND);
        int toIndex = input.indexOf(TO_COMMAND);
        if (fromIndex == -1 || toIndex == -1) {
            String errorMessage = "\tOOPS!!! Please include /from and /to for the start and end time.";
            throw new RCException(errorMessage);
        }

        String description = input.substring(0, fromIndex).trim();
        String from = input.substring(fromIndex + FROM_COMMAND.length(), toIndex).trim();
        String to = input.substring(toIndex + TO_COMMAND.length()).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            String errorMessage = "\tOOPS!!! Please ensure description, start and end time are filled.";
            throw new RCException(errorMessage);
        }

        tasks.add(new Event(description, from, to));
        tasks.get(tasks.size() - 1).printAddedTask();
        //writeToFile(FILE_PATH, tasks);
    }

    private static void addDeadline(String input, ArrayList<Task> tasks) throws RCException {
        int splitIndex = input.indexOf(BY_COMMAND);
        if (splitIndex == -1) {
            String errorMessage = "\tOOPS!!! Please include /by followed by the deadline. (eg. /by Monday)";
            throw new RCException(errorMessage);
        }

        String description = input.substring(0, splitIndex).trim();
        String by = input.substring(splitIndex + BY_COMMAND.length()).trim();
        if (description.isEmpty() || by.isEmpty()) {
            String errorMessage = "\tOOPS!!! Please ensure description and deadline are filled.";
            throw new RCException(errorMessage);
        }

        tasks.add(new Deadline(description, by));
        tasks.get(tasks.size() - 1).printAddedTask();
        //writeToFile(FILE_PATH, tasks);
    }

    private static void unmarkTask(String input, ArrayList<Task> tasks) throws RCException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            String errorMessage = "\tOOPS!!! Please enter a valid integer.";
            throw new RCException(errorMessage);
        }

        if (!Task.isValidIndex(taskNum)) {
            String errorMessage = "\tOOPS!!! Index is out of range of list.";
            throw new RCException(errorMessage);
        }

        tasks.get(taskNum).unmarkTask();
        editFile(FILE_PATH, tasks, taskNum);
    }

    private static void markTask(String input, ArrayList<Task> tasks) throws RCException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            String errorMessage = "\tOOPS!!! Please enter a valid integer.";
            throw new RCException(errorMessage);
        }

        if (!Task.isValidIndex(taskNum)) {
            String errorMessage = "\tOOPS!!! Index is out of range of list.";
            throw new RCException(errorMessage);
        }

        tasks.get(taskNum).markAsDone();
        editFile(FILE_PATH, tasks, taskNum);
    }

    private static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    private static void editFile(String filePath, ArrayList<Task> tasks, int taskNum) throws RCException {
        try {
            BufferedReader fr = new BufferedReader(new FileReader(filePath));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            int count = 0;

            while ((line = fr.readLine()) != null) {
                if (count == taskNum) {
                    if (line.charAt(4) == '0') {
                        line = line.replaceFirst("0", "1");
                    } else {
                        line = line.replaceFirst("1", "0");
                    }
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
                count++;
            }
            fr.close();

            FileOutputStream fileOut = new FileOutputStream("data/tasks.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            String message = "\tOOPS!!! Error reading file.";
            throw new RCException(message);
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws RCException {
        try {
            tasks.get(tasks.size() - 1).writeToFile(FILE_PATH);
        } catch (IOException e) {
            String errorMessage = "\tOOPS!!! Error writing to file.";
            throw new RCException(errorMessage);
        }
    }
    public static boolean isExit() {
        return isExit;
    }

    public static void setExit(boolean isExit) {
        RCCommand.isExit = isExit;
    }

    public static void handleCommand(String input, ArrayList<Task> tasks) throws RCException {
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();
        String restOfInput = split.length > 1 ? split[1] : "";

        switch (command) {
        case TODO_COMMAND:
            addTodo(restOfInput, tasks);
            break;
        case DEADLINE_COMMAND:
            addDeadline(restOfInput, tasks);
            break;
        case EVENT_COMMAND:
            addEvent(restOfInput, tasks);
            break;
        case LIST_COMMAND:
            printTaskList(tasks);
            break;
        case MARK_COMMAND:
            markTask(restOfInput, tasks);
            break;
        case UNMARK_COMMAND:
            unmarkTask(restOfInput, tasks);
            break;
        case EXIT_COMMAND:
            setExit(true);
            break;
        default:
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
