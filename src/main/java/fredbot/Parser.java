package fredbot;

import fredbot.commands.*;
import fredbot.error.*;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represent a class for parsing FredBot Commands
 */
public class Parser {
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    /**
     * Returns a tasklist object after parsing through each line in data items loaded from the file
     * @param dataItems array list of strings each representing a task
     * @return a tasklist object that is loaded with tasks from the file specified
     */
    public static TaskList parseFromFile(ArrayList<String> dataItems) { //Need to check for formatting
       TaskList tasks = new TaskList();
       for (String line: dataItems) {
           switch (line.charAt(1)) {
           case 'T':
               tasks.addTask(new Todo(line.substring(6).trim()));
               tasks.markTask(Task.getNumTask() - 1, line.charAt(4) == 'X');
               break;
           case 'D':
               String[] argumentsDeadline = line.substring(6).split("by:");

               tasks.addTask(new Deadline(argumentsDeadline[0].trim(),LocalDate.parse(argumentsDeadline[1].trim())));
               tasks.markTask(Task.getNumTask() - 1, line.charAt(4) == 'X');
               break;
           case 'E':
               String[] argumentsEvent = line.substring(6).split("to:|from:");
               LocalDate from = LocalDate.parse(argumentsEvent[1].trim());
               LocalDate to = LocalDate.parse(argumentsEvent[2].trim());
               tasks.addTask(new Event(argumentsEvent[0].trim(),
                       from, to));
               tasks.markTask(Task.getNumTask() - 1, line.charAt(4) == 'X');
               break;
           }
       }
       return tasks;
    }

    /**
     * Returns a command given the string containing the full command provided by the user after parsing it
     * @param fullCommand the string provided by the user from stdin
     * @return a command initialized appropriately specified by the string in fullcommand
     * @throws FredBotCommandErrorException when the command given is completely unrecognizable
     * @throws FredBotTodoErrorException when there is an error with the todo command provided
     * @throws FredBotDeadlineErrorException when there is an error with the deadline command provided
     * @throws FredBotEventErrorException when there is an error with the event command provided
     * @throws FredBotDeleteErrorException when there is an error with the delete command provided
     * @throws FredBotMarkErrorException when there is an error with the mark command provided
     * @throws NumberFormatException when there is an error with the number provided in mark and delete
     */
    public static Command parseCommand(String fullCommand) throws
            FredBotCommandErrorException,
            FredBotTodoErrorException,
            FredBotDeadlineErrorException,
            FredBotEventErrorException,
            FredBotDeleteErrorException,
            FredBotMarkErrorException,
            NumberFormatException {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }
        if (fullCommand.startsWith(COMMAND_MARK)) {
            int index = Integer.parseInt(fullCommand.substring(5).trim());
            if (!checkBounds(index)) {
                throw new FredBotMarkErrorException();
            }
            return new MarkCommand(index-1, true);
        }
        if (fullCommand.startsWith(COMMAND_UNMARK)) {
            int index = Integer.parseInt(fullCommand.substring(7).trim());
            if (!checkBounds(index)) {
                throw new FredBotMarkErrorException();
            }
            return new MarkCommand(index-1, false);
        }
        if (fullCommand.startsWith(COMMAND_ADD_TODO)) {
            if (!checkTodo(fullCommand.replace(COMMAND_ADD_TODO, ""))) {
                throw new FredBotTodoErrorException();
            }
            return new AddCommand(new Todo(fullCommand.replace(COMMAND_ADD_TODO, "").trim()));
        }
        if (fullCommand.startsWith(COMMAND_ADD_DEADLINE)) {
            fullCommand = fullCommand.replace(COMMAND_ADD_DEADLINE, "").trim();
            if (!checkDeadline(fullCommand)) {
                throw new FredBotDeadlineErrorException();
            }
            String[] arguments = fullCommand.split("/by");
            LocalDate by;
            try {
                by = LocalDate.parse(arguments[1].trim());
            } catch (DateTimeParseException e) {
                throw new FredBotDeadlineErrorException();
            }
            return new AddCommand(new Deadline(arguments[0].trim(), by));
        }
        if (fullCommand.startsWith(COMMAND_ADD_EVENT)) {
            fullCommand = fullCommand.replace(COMMAND_ADD_EVENT, "").trim();
            if (!checkEvent(fullCommand)) {
                throw new FredBotEventErrorException();
            }
            String[] arguments = fullCommand.split("/to|/from");
            LocalDate from;
            LocalDate to;
            try {
                from = LocalDate.parse(arguments[1].trim());
                to = LocalDate.parse(arguments[2].trim());
            } catch (DateTimeParseException e) {
                throw new FredBotEventErrorException();
            }
            return new AddCommand(new Event(arguments[0].trim(), from, to));
        }
        if (fullCommand.startsWith(COMMAND_DELETE)) {
            int index = Integer.parseInt(fullCommand.substring(6).trim());
            if (!checkBounds(index)) {
                throw new FredBotDeleteErrorException();
            }
            return new DeleteCommand(index - 1);
        }
        if (fullCommand.startsWith(COMMAND_FIND)) {
            fullCommand = fullCommand.replace(COMMAND_FIND, "").trim();
            return new FindCommand(fullCommand);
        }
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        throw new FredBotCommandErrorException();
    }

    /**
     * Returns a boolean that states whether the string provided after todo command is valid
     * @param TodoString string argument for todo command
     * @return a boolean that define the validity
     */
    public static boolean checkTodo(String TodoString) {
        return !TodoString.isEmpty();
    }

    /**
     * Returns a boolean that states whether the string provided after deadline command is valid
     * @param Deadline string argument for deadline command
     * @return a boolean that define the validity
     */
    public static boolean checkDeadline(String Deadline) {
        if (Deadline.isEmpty()) {
            return false;
        }
        String[] arguments = Deadline.split("/by");
        return arguments.length == 2;
    }

    /**
     * Returns a boolean that states whether the string provided after event command is valid
     * @param Event string argument for event command
     * @return a boolean that define the validity
     */
    public static boolean checkEvent(String Event) {
        if (Event.isEmpty()) {
            return false;
        }
        String[] arguments = Event.split("/to|/from");
        return arguments.length == 3;
    }

    /**
     * Returns a boolean that states whether the string provided after mark/delete command is valid
     * @param index argument for mark/delete command
     * @return a boolean that define the validity
     */
    public static boolean checkBounds(int index) {
        return index >= 1 && index <= Task.getNumTask();
    }
}
