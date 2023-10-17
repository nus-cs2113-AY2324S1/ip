package kenergeticbot.command;

public class BooleanChecks {
    //Returns True if the text contains "todo", False if not
    public static boolean checkTextForTodo(String item) {
        return item.startsWith("todo");
    }

    //Returns True if the text contains "deadline", False if not
    public static boolean checkTextForDeadline(String item) {
        return item.startsWith("deadline");
    }

    //Returns True if the text contains "event", False if not
    public static boolean checkTextForEvent(String item) {
        return item.startsWith("event");
    }
}
