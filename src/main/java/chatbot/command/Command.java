package chatbot.command;

import chatbot.*;

import java.util.ArrayList;

public class Command extends AbstractCommand {
    protected Storage storage;
    protected String commandType;
    protected String input;
    protected Ui ui;
    public Command(String commandType, String input) {
        this.storage = new Storage("./tasklist.txt");
        this.commandType = commandType;
        this.input = input;
        this.ui = new Ui();
    }
    /**
     * Execute the command specified by the user
     *
     * @param   tasks       the current list of tasks
     * @param   isUserInput is the command a user input
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotEmptyDescException {
        return;
    }

}
