package fredbot;

import fredbot.commands.*;
import fredbot.error.FredBotCommandErrorException;
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
               tasks.getTask(Task.getNumTask()).setDone(line.charAt(4) == 'X');
               Task.setNumTask(Task.getNumTask()+1);
               break;
           case 'D':
               String[] argumentsDeadline = line.substring(6).split("by:");
               tasks.addTask(new Deadline(argumentsDeadline[0].trim(),argumentsDeadline[1].trim()));
               tasks.getTask(Task.getNumTask()).setDone(line.charAt(4) == 'X');
               Task.setNumTask(Task.getNumTask()+1);
               break;
           case 'E':
               String[] argumentsEvent = line.substring(6).split("to:|from:");
               tasks.addTask(new Event(argumentsEvent[0].trim(),
                       argumentsEvent[1].trim(), argumentsEvent[2].trim()));
               tasks.getTask(Task.getNumTask()).setDone(line.charAt(4) == 'X');
               Task.setNumTask(Task.getNumTask()+1);
               break;
           }
       }
       return tasks;
    }

    public static Command parseCommand(String fullCommand) throws FredBotCommandErrorException {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }
        if (fullCommand.startsWith(COMMAND_MARK)) {
            int index = Integer.parseInt(fullCommand.substring(5).trim());
            return new MarkCommand(index-1, true);
        }
        if (fullCommand.startsWith(COMMAND_UNMARK)) {
            int index = Integer.parseInt(fullCommand.substring(7).trim());
            return new MarkCommand(index-1, false);
        }
        if (fullCommand.startsWith(COMMAND_ADD_TODO)){
            return new AddCommand(new Todo(fullCommand.replace(COMMAND_ADD_TODO, "").trim()));
        }
        if (fullCommand.startsWith(COMMAND_ADD_DEADLINE)) {
            fullCommand = fullCommand.replace(COMMAND_ADD_DEADLINE, "").trim();
            String[] arguments = fullCommand.split("/by");
            return new AddCommand(new Deadline(arguments[0].trim(),arguments[1].trim()));
        }
        if (fullCommand.startsWith(COMMAND_ADD_EVENT)) {
            fullCommand = fullCommand.replace(COMMAND_ADD_EVENT, "").trim();
            String[] arguments = fullCommand.split("/to|/from");
            return new AddCommand(new Event(arguments[0].trim(), arguments[1].trim(), arguments[2].trim()));
        }
        if (fullCommand.startsWith(COMMAND_DELETE)) {
            int index = Integer.parseInt(fullCommand.substring(6).trim());
            return new DeleteCommand(index - 1);
        }
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        throw new FredBotCommandErrorException();
    }
}
