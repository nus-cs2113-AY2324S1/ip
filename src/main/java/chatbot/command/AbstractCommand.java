package chatbot.command;

import chatbot.ChatbotEmptyDescException;
import chatbot.ChatbotUnknownCommandException;
import chatbot.Task;

import java.util.ArrayList;

public abstract class AbstractCommand {
    public abstract void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException;
}
