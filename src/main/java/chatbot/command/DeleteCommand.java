package chatbot.command;

import chatbot.ChatbotEmptyDescException;
import chatbot.Task;
import chatbot.Todo;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(String commandType, String input) {
        super(commandType, input);
    }
    /**
     * Execute the `delete` command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException {
        String msg = input.replace("delete", "").trim();
        if (msg.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of delete cannot be empty.");
        }
        int indexToRemove = Integer.parseInt(msg);
        if( indexToRemove > 0 ) {
            indexToRemove -= 1;
        }
        Task task = tasks.get(indexToRemove);
        tasks.remove(indexToRemove);
        if (isUserInput) {
            storage.saveToFile(input);
            ui.printDeleteResult(tasks, task);
        }
    }
}
