import command.Command;
import command.TodoCommand;
import command.ListCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.ByeCommand;
import command.DeleteCommand;

import exception.FrankException;
import exception.FrankUnknownException;

import java.util.Scanner;
public class CommandParser {
    public static Command getCommand() throws FrankException {
        Scanner input = new Scanner(System.in);
        System.out.println("Available Commands: " +
                "todo, list, deadline, event, mark <index>, unmark <index>, delete <index>, bye");
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
        case "bye":
            return new ByeCommand();
        default:
            throw new FrankUnknownException();
        }
    }
}
