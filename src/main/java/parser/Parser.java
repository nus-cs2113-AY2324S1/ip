package parser;

import data.TaskList;
import data.exception.IncompleteDescriptionException;
import data.exception.InvalidActionException;
import ui.Ui;

/**
 * Parses user input.
 */
public class Parser {
    public static final String MESSAGE_UNKNOWN = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Validates the input from the user.
     * Returns true if the user command is to exit the program.
     * @param text input text from user
     * @param tasks list of tasks
     * @return indicator to check if the user wants to exit the program
     * @throws InvalidActionException If user input is not a valid action.
     * @throws IncompleteDescriptionException If user input is incomplete.
     */
    public static boolean inputValidation (String text, TaskList tasks) throws InvalidActionException,
            IncompleteDescriptionException {
        String[] actionAndDescriptions = text.split(" ");
        String action = actionAndDescriptions[0];
        boolean isExit = false;

        switch (action){
        case "bye":
            isExit = true;
            break;
        case "list":
            Ui.printList(tasks);
            break;
        case "mark":
            tasks.markTask(Integer.parseInt(actionAndDescriptions[1]));
            break;
        case "unmark":
            tasks.unMarkTask(Integer.parseInt(actionAndDescriptions[1]));
            break;
        case "todo":
            tasks.addTodo(text);
            break;
        case "deadline":
            tasks.addDeadline(text);
            break;
        case "event":
            tasks.addEvent(text);
            break;
        case "delete":
            tasks.deleteTask(Integer.parseInt(actionAndDescriptions[1]));
            break;
        case "find":
            tasks.findTask(actionAndDescriptions[1]);
            break;
        default:
            throw new InvalidActionException(MESSAGE_UNKNOWN);
        }
        return isExit;
    }
}
