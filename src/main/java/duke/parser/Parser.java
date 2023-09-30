package duke.parser;

import duke.command.*;
import duke.exception.DukeCommandException;

import java.util.List;

public class Parser {

    public int parseIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public String[] parseTask(String input, String regex) {
        return input.split(regex);
    }

    public String[] parseCommandType(String input) {
        return input.split(" ", 2);
    }

    public Command parseCommand(String input) throws DukeCommandException {
        String[] parsedInput = parseCommandType(input);
        String commandType = parsedInput[0];
        String data = parsedInput.length == 1 ? "" : parsedInput[1].trim();
        Command command;

        switch (commandType) {
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            command = new MarkCommand(data);
            break;
        case "unmark":
            command = new UnmarkCommand(data);
            break;
        case "delete":
            command = new DeleteCommand(data);
            break;
        case "find":
            command = new FindCommand(data);
            break;
        case "todo":
            command = new AddTodoCommand(data);
            break;
        case "deadline":
            command = new AddDeadlineCommand(data);
            break;
        case "event":
            command = new AddEventCommand(data);
            break;
        case "bye":
            command = new ByeCommand();
            break;
        default:
            throw new DukeCommandException();
        }

        return command;
    }
}
