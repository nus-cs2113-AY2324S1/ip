package Parser;

import Commands.*;

import Exceptions.DukeException;
import Exceptions.DukeFormatException;
import Exceptions.DukeIndexException;

/*
 * Parser class to handle user input commands
 */
public class Parser {

    private Parser(){
    }

    /**
     * method to handle input string and return resultant command class
     * @param input string input by user
     */
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new MarkCommand(parts[1]);
                case "unmark":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new UnmarkCommand(parts[1]);
                case "todo":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new TodoCommand(parts[1]);
                case "deadline":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new DeadlineCommand(input);
                case "event":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new EventCommand(input);
                case "delete":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new DeleteCommand(parts[1]);
                case "find":
                    if (parts.length != 2) {
                        throw new DukeFormatException("☹ OOPS!!! Check your input again~");
                    }
                    return new FindCommand(parts[1]);
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :<");
            }
        } catch (DukeIndexException | DukeFormatException | DukeException e){
            System.out.println(e.getMessage());
        }
        return new Command();
    }
}
