package chatbot;

import chatbot.command.Command;

public class Parser {
    /**
     * Parse the command and returns a Command object
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public Command parseCommand(String input) throws ChatbotUnknownCommandException {
        if (input.equals("list")) {
            return new Command("list", input);
        } else if (input.startsWith("mark ")) {
            return new Command("mark", input);
        } else if (input.startsWith("unmark ")) {
            return new Command("unmark", input);
        } else if (input.startsWith("todo")) {
            return new Command("todo", input);
        } else if (input.startsWith("deadline")) {
            return new Command("deadline", input);
        } else if (input.startsWith("event")) {
            return new Command("event", input);
        } else if (input.startsWith("delete")) {
            return new Command("delete", input);
        } else if (input.startsWith("find")) {
            return new Command("find", input);
        } else {
            // unknown command
            throw new ChatbotUnknownCommandException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
