package userinputs;

public enum UserMessages {
    WELCOME_MESSAGE("hello! I'm Zran, your personal assistant:)! \n" +
            "    Type in your to dos for the day and press enter! \n " +
            "   Type 'help' to view to list of commands the bot accepts! "),
    GOODBYE_MESSAGE("Goodbye <3 Have a great day ahead!"),
    LOADING_ERROR("Unable to load the file. Please check your filepath. :)"),
    HELP_MESSAGE("Help is here! :) \n" +
            "    Listed below are the valid commands: \n" +
            "    - mark \n    - unmark \n " +
            "   - todo \n    - deadline \n    - event \n" +
            "    For the formats below, replace *field* with your input \n" +
            "    MARK: mark *existing task index* \n" +
            "    UNMARK: unmark *existing task index* \n" +
            "    TODO: todo *task name* \n" +
            "    DEADLINE: deadline *task name* /by *deadline* \n" +
            "    EVENT: event *event name* /from *start date* /to *end date* \n" +
            "    DELETE: delete *existing task index* "),
    LINE("    ____________________________________________________________"),
    ;

    public final String message;
    UserMessages(String message) {
        this.message = message;
    }
}
