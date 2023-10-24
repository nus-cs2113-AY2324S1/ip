package chatbot.command;

import chatbot.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    public MarkCommand(String commandType, String input) {
        super(commandType, input);
    }
    /**
     * Execute the `mark` command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) {
        String number = input.replace("mark ", "").trim();
        int markTaskNo = Integer.parseInt(number);
        if (markTaskNo > 0) {
            tasks.get(markTaskNo - 1).markAsDone();
        }
        if (isUserInput) {
            storage.saveToFile(input);
            ui.printMarkResult(tasks, markTaskNo);
        }
    }
}
