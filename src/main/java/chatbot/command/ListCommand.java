package chatbot.command;

import chatbot.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String commandType, String input) {
        super(commandType, input);
    }
    /**
     * Execute the `list` command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) {
        this.ui.printList(tasks);
    }
}
