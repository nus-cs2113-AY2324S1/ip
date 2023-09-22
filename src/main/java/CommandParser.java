import java.util.Scanner;
public class CommandParser {
    public static Command getCommand() throws FrankException {
        Scanner input = new Scanner(System.in);
        System.out.println("Available Commands: " +
                "list, deadline, todo, event, mark <index>, unmark <index>, " +
                "<Todo> (default), bye");
        String command = input.nextLine();
        String[] commands = command.split(" "); // if mark or unmark will be followed by an int
        switch (commands[0]) {
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
        case "todo":
            return new TodoCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new FrankUnknownException();
        }
    }
}
