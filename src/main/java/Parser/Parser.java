package Parser;

import Commands.Command;
import Commands.ByeCommand;
import Commands.ListCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Commands.TodoCommand;
import Commands.DeadlineCommand;
import Commands.EventCommand;
import Commands.DeleteCommand;

import Exceptions.DukeException;
import Exceptions.DukeFormatException;
import Exceptions.DukeIndexException;

import java.io.IOException;


public class Parser {

    private Parser(){

    }

    //method to identify command
    public static Command parse(String input) throws DukeFormatException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        try {
            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :< Check your input again~");
            }

            switch (command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(parts[1]);
                case "unmark":
                    return new UnmarkCommand(parts[1]);
                case "todo":
                    return new TodoCommand(parts[1]);
                case "deadline":
                    return new DeadlineCommand(input);
                case "event":
                    return new EventCommand(input);
                case "delete":
                    return new DeleteCommand(parts[1]);
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :<");
            }
        } catch (DukeIndexException | DukeFormatException | DukeException e){
            System.out.println(e.getMessage());
        }
        return new Command();
    }
}
