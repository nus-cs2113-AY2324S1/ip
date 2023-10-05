import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import dukey.DukeyException;

import java.util.regex.*;

public class Parser {
    public static String[] parseUserInput(String line) {
        String[] words = line.split(" ", 2);
        return words;
    }

    // parses the input line based on what type of command it is
    public static String[] parseCommandInput(String command, String input) {
        String[] words = null;  // Initialize the array
        switch (command) {
            case "deadline":
                words = input.split("/by");
                words[0] = words[0].trim();
                words[1] = words[1].trim();
                break;
            case "event":
                int startIndexOfFrom = input.indexOf("/from");
                int startIndexOfTo = input.indexOf("/to");
                final int beginIndex = 6;
                String from = input.substring(startIndexOfFrom + 5, startIndexOfTo);
                String to = input.substring(startIndexOfTo + 4);
                String description = input.substring(0, startIndexOfFrom);
                words = new String[]{from, to, description};
                break;
            case "todo":
                words = new String[]{input.trim()};
                break;
            case "bye":
                break;
            case "list":
                break;
            case "mark":
                break;
            case "unmark":
                break;
            default:
                // Handle the default case, if needed
                break;
        }

        return words;  // Return the array
    }




}

