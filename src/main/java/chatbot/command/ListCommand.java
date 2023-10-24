package chatbot.command;

import chatbot.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String commandType, String input) {
        super(commandType, input);
    }
    public void execute(ArrayList<Task> tasks, boolean isUserInput) {
        this.ui.printList(tasks);
    }
}
