package dude;

import java.util.ArrayList;

public class Parser {

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
