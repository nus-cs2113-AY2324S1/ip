package duke;

public class Parser {
    // Parse user commands
    public static Command parse(String userInput, Ui ui) throws DukeException {
        String[] words = userInput.split(" ");
        String commandType = words[0].toLowerCase();

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkTask(userInput);
            case "unmark":
                return parseUnmarkTask(userInput, ui);
            case "todo":
                return parseAddTodoTask(userInput);
            case "deadline":
                return parseAddDeadlineTask(userInput);
            case "event":
                return parseAddEventTask(userInput);
            case "delete":
                return parseDeleteTask(userInput, ui);
            default:
                throw new UnknownCommandException();
        }
    }

    private static Command parseMarkTask(String userInput) throws DukeException {
        try {
            String[] words = userInput.split(" ");

            // Checks if the user input has the correct format
            if (words.length < 2) {
                throw new DukeException("☹ OOPS!!! Please specify a task number to mark as done.");
            }

            // Parse the task index
            int taskIndex = Integer.parseInt(words[1]) - 1;

            return new MarkTaskCommand(taskIndex);
        } catch (NumberFormatException e) {
            // Handle the case where the task index is not a valid number
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to mark as done.");
        }
    }

    private static Command parseUnmarkTask(String userInput, Ui ui) throws DukeException {
        try {
            String[] words = userInput.split(" ");
            if (words.length < 2) {
                throw new DukeException("☹ OOPS!!! Please specify a task number to unmark.");
            }
            int taskIndex = Integer.parseInt(words[1]) - 1;
            return new UnmarkTaskCommand(taskIndex); // Pass the Ui object
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to unmark.");
        }
    }



    private static Command parseAddTodoTask(String userInput) throws DukeException {
        // Implement parsing for adding a todo task
        String description = userInput.replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(description);
    }

    private static Command parseAddDeadlineTask(String userInput) throws DukeException {
        // Implement parsing for adding a deadline task
        String[] parts = userInput.replaceFirst("deadline", "").trim().split("/by");
        if (parts.length != 2) {
            throw new DukeException("☹ OOPS!!! The deadline task must include '/by' to specify the date.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description and date of a deadline cannot be empty.");
        }
        return new AddDeadlineCommand(description, by);
    }

    private static Command parseAddEventTask(String userInput) throws DukeException {
        // Implement parsing for adding an event task
        String[] parts = userInput.replaceFirst("event", "").trim().split("/from");
        if (parts.length != 2 || !parts[1].contains("/to")) {
            throw new DukeException("☹ OOPS!!! The event task must include '/from' and '/to' to specify the date range.");
        }
        String description = parts[0].trim();
        String fromTo = parts[1].trim();
        String[] dates = fromTo.split("/to");
        String from = dates[0].trim();
        String to = dates[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description and date range of an event cannot be empty.");
        }
        return new AddEventCommand(description, from, to);
    }

    private static Command parseDeleteTask(String userInput, Ui ui) throws DukeException {
        // Implement parsing for deleting a task
        try {
            String[] words = userInput.split(" ");
            if (words.length < 2) {
                throw new DukeException("☹ OOPS!!! Please specify a task number to delete.");
            }
            int taskIndex = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(taskIndex, ui); // Pass the Ui object
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to delete.");
        }
    }

}

