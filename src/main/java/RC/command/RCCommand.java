package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.task.Deadline;
import RC.task.Event;
import RC.task.Task;
import RC.task.Todo;

public abstract class RCCommand {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "bye";
    private static final String BY_COMMAND = "/by";
    private static final String FROM_COMMAND = "/from";
    private static final String TO_COMMAND = "/to";
    private static boolean isExit = false;

    private static void addTodo(String input, TaskList tasks) throws RCException {
        if (input.isEmpty()) {
            String errorMessage = "\tOOPS!!! The description of a todo cannot be empty.";
            throw new RCException(errorMessage);
        }

        tasks.add(new Todo(input));
        tasks.getTask(tasks.getSize() - 1).printAddedTask();
    }

    private static void addEvent(String input, TaskList tasks) throws RCException {
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
        //tasks.getTask(tasks.getSize() - 1).printAddedTask();
    }

    private static void addDeadline(String input, TaskList tasks) throws RCException {
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
        //tasks.getTask(tasks.getSize() - 1).printAddedTask();
    }

    private static void unmarkTask(String input, TaskList tasks) throws RCException {
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

        tasks.getTask(taskNum).unmarkTask();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t  " + tasks.getTask(taskNum));
    }

    private static void markTask(String input, TaskList tasks) throws RCException {
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

        tasks.getTask(taskNum).markAsDone();
        System.out.println("\tNice! I've marked this task as done:\n\t  " + tasks.getTask(taskNum));
    }
    private static void deleteTask(String input, TaskList tasks) throws RCException {
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

        tasks.getTask(taskNum).deleteTask();
        tasks.delete(taskNum);
    }
    private static void printTaskList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTask(i));
        }
    }

    public static boolean isExit() {
        return isExit;
    }

    public static void setExit(boolean isExit) {
        RCCommand.isExit = isExit;
    }

    public static RCCommand getCommand(String input, TaskList tasks) throws RCException {
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();
        String restOfInput = split.length > 1 ? split[1] : "";

        switch (command) {
        case TODO_COMMAND:
            //addTodo(restOfInput, tasks);
            return new TodoCommand(restOfInput, tasks);
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
        case DELETE_COMMAND:
            deleteTask(restOfInput, tasks);
            break;
        case EXIT_COMMAND:
            setExit(true);
            break;
        default:
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return null;
    }

    public abstract void execute() throws RCException;
}
