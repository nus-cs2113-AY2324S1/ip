package chatbot.command;

import chatbot.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String commandType, String input) {
        super(commandType, input);
    }
    /**
     * Execute the `unmark` command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) {
        String number = input.replace("unmark ", "").trim();
        int unmarkedTaskNo = Integer.parseInt(number);
        if (unmarkedTaskNo > 0) {
            tasks.get(unmarkedTaskNo - 1).markAsUndone();
        }
        if (isUserInput) {
            storage.saveToFile(input);
            ui.printUnmarkResult(tasks, unmarkedTaskNo);
        }
    }
}
