package utility;

import command.*;
import exception.FrankException;
import exception.FrankUnknownException;
import java.util.Scanner;

public class CommandParser {
    /**
     * Method to gather user command and feed it into the right command
     *
     * @return Command with an Execute method that the main method will perform
     * @throws FrankException with an appropriate error message
     */
    public static Command getCommand() throws FrankException {
        Scanner input = new Scanner(System.in);
        System.out.println("Available Commands: " + System.lineSeparator() +
                "todo, deadline, event, " + System.lineSeparator() +
                "mark <index>, unmark <index>, delete <index>, search <terms>, " + System.lineSeparator() +
                "list, clear, bye");
        String command = input.nextLine();
        String[] commands = command.split(" "); // if mark or unmark will be followed by an int
        switch (commands[0]) {
        case "todo":
            return new TodoCommand();
        case "list":
            return new ListCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "mark":
            return new MarkCommand(command);
        case "unmark":
            return new UnmarkCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "search":
            return new SearchCommand(command);
        case "clear":
            return new ClearCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new FrankUnknownException();
        }
    }
}
