package chatbot;

import chatbot.command.*;

public class Parser {
    /**
     * Parse the command and returns a Command object
     *
     * @param   input       The input to be parsed
     * @author  Jeremy
     * @since   2023-10-06
     */
    public Command parseCommand(String input) throws ChatbotUnknownCommandException {
        if (input.equals("list")) {
            return new ListCommand("list", input);
        } else if (input.startsWith("mark ")) {
            return new MarkCommand("mark", input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand("unmark", input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand("todo", input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand("deadline", input);
        } else if (input.startsWith("event")) {
            return new EventCommand("event", input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand("delete", input);
        } else if (input.startsWith("find")) {
            return new FindCommand("find", input);
        } else {
            // unknown command
            throw new ChatbotUnknownCommandException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
