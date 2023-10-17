package userinputs;

import commands.*;

/**
 * Parser class for the Zran application.
 * Parses user's input to identify user's action.
 * Creates an instance of the corresponding command class.
 */
public class Parser {
    public static Commands parse(String input) {
        if (input.toLowerCase().startsWith(Commands.MARK_TASK_COMMAND)) {
            return new MarkTaskCommand(input);
        } else if (input.toLowerCase().startsWith(Commands.UNMARK_TASK_COMMAND)) {
            return new UnmarkTaskCommand(input);
        } else if (input.toLowerCase().startsWith(Commands.DELETE_TASK_COMMAND)){
            return new DeleteTaskCommand(input);
        } else if(input.toLowerCase().startsWith(Commands.LIST_TASK_COMMAND)) {
            return new ListTaskCommand(input);
        } else if (input.toLowerCase().startsWith(Commands.USER_HELP_COMMAND)){
            return new HelpTaskCommand(input);
        } else if (input.toLowerCase().startsWith(Commands.EXIT_BOT_COMMAND)){
            return new Commands(input);
        } else if (input.toLowerCase().startsWith(Commands.USER_FIND_COMMAND)){
            return new FindTaskCommand(input);
        } else {
            return new AddTaskCommand(input);
        }
    }
}
