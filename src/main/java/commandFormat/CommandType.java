package commandFormat;

import command.*;
import exception.DukeException;

public class CommandType {

    public static Command parseCommand(String input) throws DukeException {

        String[] commandSplits = input.split(" ");
        Command command;

        switch (commandSplits[0]) {
        case "list":
            command = new ListCommand();
            break;
        case "todo":
            command = new TodoCommand(input);
            break;
        case "deadline":
            command = new DeadlineCommand(input);
            break;
        case "event":
            command = new EventCommand(input);
            break;
        case "mark":
            command = new MarkCommand(commandSplits[1]);
            break;
        case "unmark":
            command = new UnmarkCommand(commandSplits[1]);
            break;
        case "delete":
            command = new DeleteCommand(commandSplits[1]);
            break;
        case "bye":
            command = new ByeCommand();
            break;
        default:
            throw new DukeException();
        }

        return command;
    }


}
