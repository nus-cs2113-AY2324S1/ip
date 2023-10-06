package alice.parser;

import alice.exceptions.InvalidCommandException;
import alice.exceptions.InvalidDateTimeException;
import alice.exceptions.InvalidFormatException;
import alice.tasks.*;

import java.time.LocalDateTime;

public class TaskParser{
    public String actionCommand;
    public String userInput;

    public TaskParser(String userInput) {
        this.userInput = userInput;
        this.actionCommand = userInput.split(" ")[0];
    }

    String getUserInput() {
        return userInput;
    }

    /**
     * Creates a Deadline task object
     *
     * @return Deadline object
     * @throws InvalidFormatException the format of the user input is wrong
     */
    public Task newDeadline() throws InvalidFormatException, InvalidDateTimeException {
        if (!userInput.contains("/by")) {
            throw new InvalidFormatException();
        }

        String[] inputArray = getUserInput().split(" /by");
        if (inputArray.length == 1) {
            throw new InvalidFormatException();
        }

        final int LENGTH_OF_COMMAND = 9; //length of "deadline "
        String description = inputArray[0].substring(LENGTH_OF_COMMAND).trim();
        String date = inputArray[1].strip();
        LocalDateTime formattedDateTime = DateTimeParser.formatDateTime(date);

        return new Deadline(description, formattedDateTime);
    }


    /**
     * Creates an Event task object
     *
     * @return Event object
     * @throws InvalidFormatException the format of the user input is wrong
     */
    public Task newEvent() throws InvalidFormatException, InvalidDateTimeException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new InvalidFormatException();
        }

        final int LENGTH_OF_COMMAND = 6; //length of "event "
        String description = getUserInput().split(" /from")[0].substring(LENGTH_OF_COMMAND).trim();
        String start = getUserInput().split(" /from")[1].split(" /to")[0].trim();
        String end = getUserInput().split(" /to")[1].trim();

        LocalDateTime formattedStart = DateTimeParser.formatDateTime(start);
        LocalDateTime formattedEnd = DateTimeParser.formatDateTime(end);

        return new Event(description, formattedStart, formattedEnd);
    }

    /**
     * Creates a Todo task object
     *
     * @return the Todo object
     * @throws InvalidFormatException the format of the user input is wrong
     */
    public Task newTodo() throws InvalidFormatException {
        if (userInput.split(" ").length < 2) {
            throw new InvalidFormatException();
        }
        final int LENGTH_OF_COMMAND = 5; //length of "size "

        String description = getUserInput().substring(LENGTH_OF_COMMAND);
        return new Todo(description);
    }

    /**
     * Creates a task object corresponding to the action command in the input
     *
     * @return Taks object
     * @throws InvalidFormatException the format of the input is invalid
     * @throws InvalidCommandException the command of the input is invalid
     */
    public Task createTask() throws InvalidFormatException, InvalidCommandException, InvalidDateTimeException {
        switch (actionCommand) {
        case "todo":
            return newTodo();
        case "deadline":
            return newDeadline();
        case "event":
            return newEvent();
        default:
            throw new InvalidCommandException();
        }
    }

}
