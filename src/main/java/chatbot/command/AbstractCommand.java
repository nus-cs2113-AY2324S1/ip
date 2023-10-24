package chatbot.command;

import chatbot.ChatbotEmptyDescException;
import chatbot.ChatbotUnknownCommandException;
import chatbot.Task;

import java.util.ArrayList;

public abstract class AbstractCommand {
    /**
     * Abstract method to execute the command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public abstract void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException;
}
