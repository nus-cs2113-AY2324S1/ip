package chatbot.command;

import chatbot.ChatbotEmptyDescException;
import chatbot.Deadline;
import chatbot.Task;
import chatbot.Todo;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String commandType, String input) {
        super(commandType, input);
    }
    /**
     * Execute the `deadline` command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException {
        String msg = input.replace("deadline", "").trim();
        if (msg.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String byDate = msg.substring(msg.indexOf("/by ") + 4).trim(); // will contain the byDate
        if (byDate.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The /by argument of a deadline cannot be empty.");
        }
        String desc = msg.substring(0, msg.indexOf("/by")); // will contain the deadline description
        if (desc.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        Task task = new Deadline(desc, byDate);
        tasks.add(task);
        if (isUserInput) {
            storage.saveToFile(input);
            ui.printDeadlineResult(tasks, task);
        }
    }
}
