package chatbot.command;

import chatbot.ChatbotEmptyDescException;
import chatbot.Task;
import chatbot.Todo;

import java.util.ArrayList;

public class TodoCommand extends Command {
    public TodoCommand(String commandType, String input) {
        super(commandType, input);
    }
    public void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException {
        String msg = input.replace("todo", "").trim();
        if (msg.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Task task = new Todo(msg);
        tasks.add(task);

        if (isUserInput) {
            storage.saveToFile(input);
            ui.printTodoResult(tasks, task);
        }
    }
}
