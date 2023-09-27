package elgin.ui;

public class Ui {

    final static String BOT_NAME = "Elgin";
    final static String SEPARATOR = "____________________________________________________________";

    public static void sayGreeting() {
        String[] toPrint = new String[]{
                "Hello! I'm " + BOT_NAME,
                "What can I do for you?"
        };
        formatPrint(toPrint);
    }

    public static void sayBye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    public static void formatPrint(String[] lines) {
        System.out.println("\t" + SEPARATOR);
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("\t" + SEPARATOR);
    }

    public static void formatPrint(String line) {
        System.out.println("\t" + SEPARATOR);
        System.out.println("\t" + line);
        System.out.println("\t" + SEPARATOR);
    }

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
