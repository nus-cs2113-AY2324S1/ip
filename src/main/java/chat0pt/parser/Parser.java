package chat0pt.parser;

import chat0pt.Duke;
import chat0pt.commands.*;
import chat0pt.helper.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String pattern = "yyyy-MM-dd HHmm";

    private static String[] splitString(String input) {
        return input.split(" ");
    }

    /**
     * Command parser to determine which command to execute.
     * @param command Takes in the user input and splits it
     * @return Returns a command object based on what command is to be executed
     * @throws DukeException
     */
    public Command parse(String command) throws DukeException {
        String[] separatedString = splitString(command);
        String userCommand = separatedString[0].toLowerCase();
        switch (userCommand) {
        case "mark":
            return new MarkerCommand(separatedString, true);
        case "unmark":
            return new MarkerCommand(separatedString, false);
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(separatedString);
        case "bye":
            return new EndCommand();
        case "find":
            return new FindCommand(separatedString);
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(userCommand, separatedString);
        default:
            return new InvalidCommand();
        }

    }

    public static int numberParser(String input) throws DukeException {
        int key;
        try {
            key = Integer.parseInt(input);
            key -= 1;
        } catch (NumberFormatException nfe) {
            throw new DukeException("Your number input is invalid :(");
        }
        return key;
    }

    public static int validNumberInput(String[] tokens) throws DukeException {
        int key = -1;
        if (tokens.length == 2) {
            key = numberParser(tokens[1]);
        } else {
            throw new DukeException("Your mark/unmark command has to be in the format: mark/unmark <number>");
        }
        return key;
    }

    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDateTime datetime = LocalDateTime.parse(input, format);
            return datetime;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
