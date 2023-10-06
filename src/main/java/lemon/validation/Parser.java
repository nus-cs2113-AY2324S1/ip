package lemon.validation;

import lemon.commands.*;
import lemon.exception.LemonException;
import lemon.task.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lemon.common.Messages.MESSAGE_EMPTY_LIST;

public class Parser {
    public Parser() {
    }

    public String commandType(String input) {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.matches("todo\\b.*")) {
            return "todo";
        } else if (input.matches("deadline\\b.*")) {
            return "deadline";
        } else if (input.matches("event\\b.*")) {
            return "event";
        } else if (input.matches("delete\\b.*")) {
            return "delete";
        } else if (input.matches("mark\\b.*")) {
            return "mark";
        } else if (input.matches("unmark\\b.*")) {
            return "unmark";
        } else if (input.matches("find\\b.*")) {
            return "find";
        } else if (input.equals("help")) {
            return "help";
        } else {
            return "invalid";
        }
    }

    public Command parseInput(String input) throws LemonException {
        try {
            String commandType = commandType(input.toLowerCase().trim());

            String[] taskStr = input.split("\\s+");
            String args = "";

            if (taskStr.length > 1) {
                args = taskStr[1].trim();
            }

            return createCommand(commandType, input, args);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new LemonException("Uh-oh! Invalid input format. Please try again!");
        }
    }

    public Command createCommand(String commandType, String input, String args) throws LemonException {
        switch (commandType) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case TodoCommand.COMMAND_WORD:
            return parseTodo(input);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(input);
        case EventCommand.COMMAND_WORD:
            return parseEvent(input);
        case DeleteCommand.COMMAND_WORD:
            int deleteIndex = checkTaskNumberFormat(args);
            return new DeleteCommand(deleteIndex);
        case MarkCommand.COMMAND_WORD:
            int markIndex = checkTaskNumberFormat(args);
            return new MarkCommand(markIndex);
        case UnmarkCommand.COMMAND_WORD:
            int unmarkIndex = checkTaskNumberFormat(args);
            return new UnmarkCommand(unmarkIndex);
        case FindCommand.COMMAND_WORD:
            String keyword = args.trim();
            return new FindCommand(keyword);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            return new InvalidCommand();
        }
    }

    public Task parseFile(String task) throws LemonException {
        try {
            String[] taskStr = task.split(" \\| ");

            if (taskStr.length < 3) {
                throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                        "'<T/D/E> | <0/1> | <task> [| <date/time>] [| <date/time>]'!\n");
            }

            String taskType = taskStr[0].trim();
            String taskStatus = taskStr[1].trim();
            boolean isDone = taskStr[1].trim().equals("1");
            String description = taskStr[2].trim();

            if (!taskStatus.equals("0") && !taskStatus.equals("1")) {
                throw new LemonException("Uh-oh! Data in the file is not of the right task type! " +
                        "Please use <0/1> for the task status!\n");
            }

            return createTask(taskStr, taskType, description, isDone);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                    "'<T/D/E> | <0/1> | <task> [| <date/time>] [| <date/time>]'!\n");
        }
    }

    public Task createTask(String[] taskStr, String taskType, String description, boolean isDone) throws LemonException{
        try {
            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = taskStr[3].trim();
                return new Deadline(description, by, isDone);
            case "E":
                String from = taskStr[3].trim();
                String to = taskStr[4].trim();
                return new Event(description, from, to, isDone);
            default:
                throw new LemonException("Uh-oh! Data in the file is not of the right task type! " +
                        "Please use <T/D/E> for the type of task!\n");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                    "'<T/D/E> | <0/1> | <task> [| <date/time>] [| <date/time>]'!\n");
        }
    }

    private static Command parseTodo(String input) throws LemonException {
        String inputPattern = "todo (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String description;

        if (matchFound) {
            description = matcher.group(1).trim();

            if (description.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task!");
            }
        } else {
            throw new LemonException("Oopsie! Please use the format 'todo <task>'!");
        }
        return new TodoCommand(description);
    }

    private static Command parseDeadline(String input) throws LemonException {
        String inputPattern = "deadline (.+?) /by (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String description, dateTime;

        if (matchFound) {
            description = matcher.group(1).trim();
            dateTime = matcher.group(2).trim();

            if (description.isEmpty() || dateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task and date/time!");
            }
        } else {
            throw new LemonException("Oopsie! Please use the format 'deadline <task> /by <date/time>'!");
        }
        return new DeadlineCommand(description, dateTime);
    }

    private static Command parseEvent(String input) throws LemonException {
        String inputPattern = "event (.+?) /from (.+?) /to (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String description, startDateTime, endDateTime;

        if (matchFound) {
            description = matcher.group(1).trim();
            startDateTime = matcher.group(2).trim();
            endDateTime = matcher.group(3).trim();

            if (description.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task, starting date/time and ending date/time!");
            }
        } else {
            throw new LemonException("Oopsie! Please use the format 'event <task> " +
                    "/from <starting date/time> /to <ending date/time>'!");
        }
        return new EventCommand(description, startDateTime, endDateTime);
    }

    private static int checkTaskNumberFormat(String input) throws LemonException {
        int taskIndex;
        try{
            taskIndex = Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new LemonException("Oopsie! Please enter a valid task number!");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new LemonException("Oopsie! Please enter a task number that is on the list!");
        }
        return taskIndex;
    }

    public void checkTaskNumberRange(int taskIndex, TaskList tasks) throws LemonException {
        if (tasks.isEmpty()) {
            throw new LemonException(MESSAGE_EMPTY_LIST);
        } else if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new LemonException("Oopsie! Please enter a task number that is on the list!");
        }
    }
}
