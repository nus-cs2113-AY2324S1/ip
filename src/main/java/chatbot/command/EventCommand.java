package chatbot.command;

import chatbot.ChatbotEmptyDescException;
import chatbot.Event;
import chatbot.Task;
import chatbot.Todo;

import java.util.ArrayList;

public class EventCommand extends Command {
    public EventCommand(String commandType, String input) {
        super(commandType, input);
    }
    /**
     * Execute the `event` command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException {
        String msg = input.replace("event", "").trim();
        if (msg.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a event cannot be empty.");
        }

        String dateRange = msg.substring(msg.indexOf("/from ") + 6).trim();
        if (dateRange.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The date range of a event cannot be empty.");
        }
        String fromDate = dateRange.substring(0, dateRange.indexOf("/to ")).trim();
        if (fromDate.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The /from argument cannot be empty.");
        }
        String toDate = dateRange.substring(dateRange.indexOf("/to ") + 4).trim();
        if (toDate.isEmpty()) {
            throw new ChatbotEmptyDescException(" ☹ OOPS!!! The /to argument cannot be empty.");
        }
        String desc = msg.substring(0, msg.indexOf("/from ")); // will contain the deadline description

        Task task = new Event(desc, fromDate, toDate);
        tasks.add(task);
        if (isUserInput) {
            storage.saveToFile(input);
            ui.printEventResult(tasks, task);
        }
    }
}
