package dude;

import java.util.ArrayList;

/**
 * The `Parser` class is responsible for parsing user input commands and executing the
 * corresponding actions on the task list.
 */
public class Parser {

    /**
     * Parses the user input command and performs the corresponding action on the task list.
     *
     * @param input   The user input command to parse and execute.
     * @param tasks   The TaskList instance containing the list of tasks.
     * @param ui      The Ui instance for user interactions and output.
     * @param storage The Storage instance for saving and loading tasks.
     * @throws DudeException If there's an error during command parsing or execution.
     */
    public static void parse(String input, TaskList tasks, Ui ui, Storage storage) throws DudeException {
        String[] commandWords = input.split(" ");
        String commandType = commandWords[0];

        switch (commandType) {
        case "bye":
            ui.showFarewell();
            System.exit(0);
            break;
        case "list":
            tasks.listTasks();
            break;
        case "todo":
            tasks.addTodoTask(input);
            break;
        case "deadline":
            tasks.addDeadlineTask(input);
            break;
        case "event":
            tasks.addEventTask(input);
            break;
        case "mark":
            tasks.markOrUnmarkTask(input, true); // Mark as done
            break;
        case "unmark":
            tasks.markOrUnmarkTask(input, false); // Mark as not done
            break;
        case "delete":
            tasks.deleteTask(input);
            break;
        case "find":
            String keyword = input.substring(5).trim(); // Extract the search keyword
            ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword); // Find matching tasks
            ui.showFoundTasks(matchingTasks); // Display matching tasks
            break;
        default:
            throw new DudeException("I'm sorry, I don't know what that means :-(");
        }
    }
}
