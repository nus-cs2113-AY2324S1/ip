package commandFormat;

import command.*;
import exception.OrientoException;

public class CommandType {

    /**
     * The parseCommand will parse the user input and generate a respective command object
     * e.g. if the user command aims to add a todo task, it should return a todo command
     * @param input represents raw user command
     * @return Command object of correct type
     * @throws OrientoException if input is in none of the expected cases
     */
    public static Command parseCommand(String input) throws OrientoException {

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
        case "find":
            command = new FindCommand(commandSplits[1]);
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
        case "help":
            command = new HelpCommand();
            break;
        default:
            throw new OrientoException();
        }

        return command;
    }


}
