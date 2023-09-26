package fredbot;

import fredbot.commands.*;
import fredbot.error.*;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.util.ArrayList;

public class Parser {
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static TaskList parseFromFile(ArrayList<String> dataItems) {
       TaskList tasks = new TaskList();
       for (String line: dataItems) {
           switch (line.charAt(1)) {
           case 'T':
               tasks.addTask(new Todo(line.substring(6).trim()));
               tasks.markTask(Task.getNumTask() - 1, line.charAt(4) == 'X');
               break;
           case 'D':
               String[] argumentsDeadline = line.substring(6).split("by:");
               tasks.addTask(new Deadline(argumentsDeadline[0].trim(),argumentsDeadline[1].trim()));
               tasks.markTask(Task.getNumTask() - 1, line.charAt(4) == 'X');
               break;
           case 'E':
               String[] argumentsEvent = line.substring(6).split("to:|from:");
               tasks.addTask(new Event(argumentsEvent[0].trim(),
                       argumentsEvent[1].trim(), argumentsEvent[2].trim()));
               tasks.markTask(Task.getNumTask() - 1, line.charAt(4) == 'X');
               break;
           }
       }
       return tasks;
    }

    public static Command parseCommand(String fullCommand) throws
            FredBotCommandErrorException,
            FredBotTodoErrorException,
            FredBotDeadlineErrorException,
            FredBotEventErrorException,
            FredBotDeleteErrorException,
            FredBotMarkErrorException {
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
            return new AddCommand(new Deadline(arguments[0].trim(),arguments[1].trim()));
        }
        if (fullCommand.startsWith(COMMAND_ADD_EVENT)) {
            fullCommand = fullCommand.replace(COMMAND_ADD_EVENT, "").trim();
            if (!checkEvent(fullCommand)) {
                throw new FredBotEventErrorException();
            }
            String[] arguments = fullCommand.split("/to|/from");
            return new AddCommand(new Event(arguments[0].trim(), arguments[1].trim(), arguments[2].trim()));
        }
        if (fullCommand.startsWith(COMMAND_DELETE)) {
            int index = Integer.parseInt(fullCommand.substring(6).trim());
            if (!checkBounds(index)) {
                throw new FredBotDeleteErrorException();
            }
            return new DeleteCommand(index - 1);
        }
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        throw new FredBotCommandErrorException();
    }

    public static boolean checkTodo(String TodoString) {
        return !TodoString.isEmpty();
    }

    public static boolean checkDeadline(String Deadline) {
        if (Deadline.isEmpty()) {
            return false;
        }
        String[] arguments = Deadline.split("/by");
        return arguments.length == 2;
    }

    public static boolean checkEvent(String Event) {
        if (Event.isEmpty()) {
            return false;
        }
        String[] arguments = Event.split("/to|/from");
        return arguments.length == 3;
    }

    public static boolean checkBounds(int index) {
        return index >= 1 && index <= Task.getNumTask();
    }
}
