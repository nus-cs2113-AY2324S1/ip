package elgin.utils;

public class ExceptionMessage {

    public static String getUnknownCmdErrorMsg() {
        return "Sorry I do not understand your command";
    }

    public static String getEmptyTaskIdxErrorMsg() {
        return "Please enter a task number.";
    }

    public static String getInvalidIdxErrorMsg() {
        return "Please enter a valid task number.";
    }

    public static String getEmptyDescErrorMsg(String command) {
        return "OOPS! Description of " + command + " cannot be empty";
    }

    public static String getDeadlineUsageMsg() {
        return "Usage: deadline <task> /by <deadline>";
    }

    public static String getEventUsageMsg() {
        return "Usage: event <task> /from <time> /to <time>";
    }
}
