import java.util.Scanner;

public class CommandParser {

    public static Command getCommand() throws FrankException {

        final String SOLIDLINE = "\n------------------------------------------------------------------------------------------------------------------------------\n";
        TaskList user = new TaskList();
        Scanner input = new Scanner(System.in);

        String command, description, dueDate, startDate, endDate;
        System.out.println("Available Commands: " +
                "list, deadline, todo, event, mark <index>, unmark <index>, " +
                "<Todo> (default), bye");
        command = input.nextLine();
        String[] commands = command.split(" "); // if mark or unmark will be followed by an int
        switch (commands[0]) {
        case "list":
            return new ListCommand();
        case "deadline":
            System.out.println("Bisa! What is the task?");
            description = input.nextLine();
            System.out.println("Ke Yi! When is it due?");
            dueDate = input.nextLine();
            user.addTask(new Deadline(description, dueDate));
            break;
        case "event":
            System.out.println("Boleh! What is the event?");
            description = input.nextLine();
            System.out.println("When does it start?");
            startDate = input.nextLine();
            System.out.println("When does it end?");
            endDate = input.nextLine();
            user.addTask(new Event(description, startDate, endDate));
            break;
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
        return null;
    }
    private static Todo getTodo (String input) throws FrankException {
        return new Todo("gay");
    }
}
