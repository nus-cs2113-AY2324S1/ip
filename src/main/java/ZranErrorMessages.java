public enum ZranErrorMessages {
    INVALID_TASK_DESCRIPTION("Invalid task command: Empty name of the task. \n" +
            "    Please key in the description of the task or type help :)"),
    INVALID_DEADLINE_FORMAT("Invalid deadline command: missing '/by'. \n" +
            "    Please key in the deadline after '/by' or type help :)"),
    EMPTY_DEADLINE("Invalid event command: Empty deadline of the task. \n" +
            "    Please key in the deadline of the task or type help :)"),
    INVALID_EVENT_FORMAT("Invalid event command: missing '/from' or '/to'. \n" +
            "    Please key in the duration of the event using '/from' and '/to' or type help :)"),
    EMPTY_EVENT_DURATION("Invalid event command: Empty duration of the event. \n" +
            "    Please key in the duration of the event or type help :)"),
    UNRECOGNISED_COMMAND("Invalid command: Unknown command type. \n" +
            "    Please use a valid command or type help :)"),
    EMPTY_TASK_INDEX("Invalid mark command: Missing task index to mark. \n " +
            "   Please enter a valid index to mark as done or type help :)"),
    INVALID_TASK_INDEX("Invalid mark command: Invalid task index to mark. \n" +
            "    Please enter a valid task index to mark as set or type help :)");

    final String message;
    ZranErrorMessages(String message) {
        this.message = message;
    }
}
