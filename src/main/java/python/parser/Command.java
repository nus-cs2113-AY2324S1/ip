package python.parser;

/**
 * Defines the commands expected from the user
 */
public class Command {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_FIND = "find";

    /**
     * Returns whether the given command is "bye" command or not
     *
     * @param command The command to check
     * @return Returns whether the given command is "bye" command or not
     */
    public static boolean isCommandBye(String command) {
        return (command.equals(COMMAND_BYE));
    }
}
