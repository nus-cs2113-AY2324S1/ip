package userinputs;

/**
 * Enumerates messages to be displayed to users for Zran application exceptions.
 */
public enum UserMessages {
    WELCOME_MESSAGE("    hello! I'm Zran, your personal assistant:)! \n" +
            "    Type in your to dos for the day and press enter! \n " +
            "   Type 'help' to view to list of commands the bot accepts! "),
    GOODBYE_MESSAGE("    Goodbye <3 Have a great day ahead!"),
    LOADING_ERROR("    Unable to load the file. Please check your filepath. :)"),

    HELP_MESSAGE("    Help is here! :) \n" +
            "    Listed below are the valid commands: \n" +
            "    - todo \n" +
            "    - deadline \n" +
            "    - event \n" +
            "    - mark \n" +
            "    - unmark \n" +
            "    - delete \n" +
            "    - find \n" +
            "    - help \n" +
            "    - bye \n" +
            "    For the formats below, replace *field* with your input \n" +
            "    TODO: todo *task name* \n" +
            "    DEADLINE: deadline *task name* /by *deadline* \n" +
            "    EVENT: event *event name* /from *start date* /to *end date* \n" +
            "    LIST: list\n" +
            "    MARK: mark *existing task index* \n" +
            "    UNMARK: unmark *existing task index* \n" +
            "    DELETE: delete *existing task index* \n" +
            "    FIND: find *keyword*\n" +
            "    HELP: help\n" +
            "    BYE: bye"),
    LINE("    ____________________________________________________________"),
    ;

    public final String message;
    UserMessages(String message) {
        this.message = message;
    }
}
