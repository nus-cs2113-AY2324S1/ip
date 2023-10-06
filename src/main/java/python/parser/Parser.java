package python.parser;

public class Parser {
    public static String extractCommandFromInputLine(String inputLine) {
        return inputLine.split("\\s+")[0];
    }

    public static String extractCommandDescFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    public static int extractTaskNoFromInputLine(String inputLine) {
        return Integer.parseInt(inputLine.split(" ")[1]);
    }

    public static String extractTodoDescFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    public static String extractDeadlineDetailsFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    public static String extractDeadlineDescFromDeadlineDetails(String deadlineDetails) {
        return deadlineDetails.split(" /by ")[0];
    }

    public static String extractDeadlineByFromDeadlineDetails(String deadlineDetails) {
        return deadlineDetails.split(" /by ")[1];
    }

    public static String extractEventDetailsFromInputLine(String inputLine) {
        return inputLine.split(" ", 2)[1];
    }

    public static String extractEventDescFromEventDetails(String eventDetails) {
        return eventDetails.split("\\s+/from\\s+|\\s+/to\\s+", 3)[0];
    }

    public static String extractEventFromFromEventDetails(String eventDetails) {
        return eventDetails.split("\\s+/from\\s+|\\s+/to\\s+", 3)[1];
    }

    public static String extractEventToFromEventDetails(String eventDetails) {
        return eventDetails.split("\\s+/from\\s+|\\s+/to\\s+", 3)[2];
    }
}
