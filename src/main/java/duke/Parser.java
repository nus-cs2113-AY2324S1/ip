package duke;

public class Parser {
    public static Command parse(String input) throws DukeException {
        // Split the input into words
        String[] words = input.split(" ");
        String command = words[0].toLowerCase(); // Extract the first word as the command

        switch (command) {
        case "bye":
            return new ExitCommand(); // Create an ExitCommand
        case "list":
            return new ListCommand(); // Create a ListCommand
        case "mark":
            return new MarkCommand(input); // Create a MarkCommand with the input as a parameter
        case "unmark":
            return new UnmarkCommand(input); // Create an UnmarkCommand with the input as a parameter
        case "delete":
            return new DeleteCommand(input); // Create a DeleteCommand with the input as a parameter
        case "find":
            return new FindCommand(input); // Create a FindCommand with the input as a parameter
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(input);
        default:
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

