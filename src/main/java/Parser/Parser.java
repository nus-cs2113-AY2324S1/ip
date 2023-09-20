package Parser;

import Command.Command;
import Command.TodoCommand;
import Command.DeadlineCommand;
import Command.InvalidCommand;
import Command.EventCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.DeleteCommand;
import Command.ExitCommand;

public class Parser {
    public Command parseCommand(String userInput) {
        String[] tokens = userInput.split(" ");
        if (tokens.length == 0) {
            return new InvalidCommand("Oops, your command is not recognized, please try again!");
        }

        final String commandWord = tokens[0];
        final String commandArguments = userInput.replace(tokens[0], "").strip();

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return parseToDoCommand(commandArguments);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadlineCommand(commandArguments);
        case EventCommand.COMMAND_WORD:
            return parseEventCommand(commandArguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD_DONE:
            return parseMarkCommand(commandArguments, MarkCommand.MARK_DONE);
        case MarkCommand.COMMAND_WORD_UNDONE:
            return parseMarkCommand(commandArguments, MarkCommand.MARK_UNDONE);
        case DeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(commandArguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand("Oops, your command is not recognized, please try again!");
        }
    }

    private Command parseToDoCommand(String arguments) {
        if (arguments.isEmpty()) {
            return new InvalidCommand("Oops, Todo task cannot be empty!");
        }
        return new TodoCommand(arguments);
    }

    private Command parseDeadlineCommand(String arguments) {
        try {
            final String[] deadlineTokens = arguments.split(" /by ");
            return new DeadlineCommand(deadlineTokens[0], deadlineTokens[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand("Oops, please try deadline <task> /by <deadline>!");
        }
    }

    private Command parseEventCommand(String arguments) {
        try {
            String[] eventTokens = arguments.split(" /");
            String from = eventTokens[1].replace("from ", "");
            String by = eventTokens[2].replace("to ", "");
            return new EventCommand(eventTokens[0], from, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand("Oops, please try event <task> /from <start> /to <end>!");
        }
    }

    private Command parseMarkCommand(String arguments, boolean isDone) {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(isDone, taskIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Oops, please try mark <taskIndex> or unmark <taskIndex>!");
        }
    }

    private Command parseDeleteCommand(String arguments) {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Oops, please try delete <taskIndex>!");
        }
    }
}
