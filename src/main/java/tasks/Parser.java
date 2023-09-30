package tasks;

public class Parser {

    public static String parse(String fullCommand) throws DukeException {
        String trimmedCommand = fullCommand.trim(); // Remove leading and trailing whitespaces
        String commandLowerCase = trimmedCommand.toLowerCase(); // Convert command to lowercase for case-insensitive matching

        if (commandLowerCase.equals("bye")) {
            return "exit";
        } else if (commandLowerCase.equals("list")) {
            return "list";
        } else if (commandLowerCase.startsWith("done ")) {
            return "markAsDone-" + trimmedCommand.substring(5);
        } else if (commandLowerCase.startsWith("delete")) {
            if (commandLowerCase.length() <= 6 || commandLowerCase.substring(6).trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! Please provide a task number to delete.");
            }
            return "delete-" + trimmedCommand.substring(7);
        } else if (commandLowerCase.startsWith("todo")) {
            if (commandLowerCase.length() <= 4 || commandLowerCase.substring(4).trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return "addTodo-" + trimmedCommand.substring(5);
        } else if (commandLowerCase.startsWith("deadline ")) {
            // Additional parsing for deadline command
            return "addDeadline-" + trimmedCommand.substring(9);
        } else if (commandLowerCase.startsWith("event ")) {
            // Additional parsing for event command
            return "addEvent-" + trimmedCommand.substring(6);
        } else {
            throw new DukeException("Unknown command");
        }
    }
}

