package duke.parser;

import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.exception.DukeCommandException;

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
        case "todo":
            command = new AddTodoCommand(data);
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
