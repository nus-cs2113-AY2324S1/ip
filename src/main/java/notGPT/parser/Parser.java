package notGPT.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing user commands.
 */
public class Parser {
    
    /**
     * Parses a user command into an array of strings based on space as the delimiter.
     *
     * @param command The user command to be parsed.
     * @return An array of strings containing the parsed command components.
     */
    public String[] parseCommand(String command) {
        return command.split(" ");
    }

    /**
     * Parses a string into a LocalDateTime object.
     * @param dateTime The string to be parsed.
     * @return A LocalDateTime object.
     */
    public LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateTime = null;
        parsedDateTime = LocalDateTime.parse(dateTime, formatter);
        return parsedDateTime;
    }

    /**
     * Format a LocalDateTime object into a string.
     * @param dateTime The LocalDateTime object to be parsed.
     * @return A string representing the LocalDateTime object.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }
}
