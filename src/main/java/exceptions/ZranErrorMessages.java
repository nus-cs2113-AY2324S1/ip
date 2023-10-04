package exceptions;

/**
 * Enumerates error messages for Zran application exceptions.
 * Each constant is an error scenario, paired with its respective error message.
 */
public enum ZranErrorMessages {
    INVALID_TASK_DESCRIPTION("    Invalid task command: Empty name of the task. \n" +
            "    Please key in the description of the task or type help :)"),
    INVALID_DEADLINE_FORMAT("    Invalid deadline command: missing '/by'. \n" +
            "    Please key in the deadline after '/by' or type help :)"),
    EMPTY_DEADLINE("    Invalid event command: Empty deadline of the task. \n" +
            "    Please key in the deadline of the task or type help :)"),
    INVALID_EVENT_FORMAT("    Invalid event command: missing '/from' or '/to'. \n" +
            "    Please key in the duration of the event using '/from' and '/to' or type help :)"),
    EMPTY_EVENT_DURATION("    Invalid event command: Empty duration of the event. \n" +
            "    Please key in the duration of the event or type help :)"),
    UNRECOGNISED_COMMAND("    Invalid command: Unknown command type. \n" +
            "    Please use a valid command or type help :)"),
    EMPTY_TASK_INDEX("    Invalid (un)mark command: Missing task index to (un)mark/delete. \n " +
            "   Please enter an existing index to (un)mark/delete or type help :)"),
    INVALID_TASK_INDEX("    Invalid command: Invalid task index to (un)mark/delete. \n" +
            "    Please enter an existing task index to (un)mark/delete or type help :)"),

    INVALID_DATE_FORMAT("    You can also enter a date in the format yyyy-MM-dd (e.g 2023-01-01) \n" +
            "    to display it in dd MMM yyyy format (e.g 1 JAN 2023) " +
            "or type help :)");

    public final String message;
    ZranErrorMessages(String message) {
        this.message = message;
    }
}
