package alice.parser;

import alice.exceptions.InvalidCommandException;
import alice.exceptions.InvalidFormatException;
import alice.tasks.*;

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
     * Create a new deadline task in the correct format,
     * then, calls addTask to add deadline task to alice.tasks array
     * @return Deadline object
     * @throws InvalidFormatException
     */
    public Task newDeadline() throws InvalidFormatException {
        final int LENGTH_OF_COMMAND = 9; //length of "deadline "

        String[] inputArray = getUserInput().split(" /");
        if (inputArray.length == 1) {
            throw new InvalidFormatException();
        }

        String description = inputArray[0].substring(LENGTH_OF_COMMAND);
        String date = inputArray[1];

        return new Deadline(description, date);
    }


    /**
     * Create a new event task in the correct format,
     * then, calls addTask to add event task to alice.tasks array
     * @return Event object
     * @throws InvalidFormatException
     */
    public Task newEvent() throws InvalidFormatException{
        final int LENGTH_OF_COMMAND = 6; //length of "event "

        String[] inputArray = getUserInput().split(" /");
        if (inputArray.length < 3) {
            throw new InvalidFormatException();
        }

        String description = inputArray[0].substring(LENGTH_OF_COMMAND);
        String startDate = inputArray[1].strip();
        String endDate = inputArray[2].strip();

        return new Event(description, startDate, endDate);
    }

    public Task newTodo() throws InvalidFormatException {
        if (userInput.split(" ").length < 2) {
            throw new InvalidFormatException();
        }
        final int LENGTH_OF_COMMAND = 5; //length of "size "

        String description = getUserInput().substring(LENGTH_OF_COMMAND);
        return new Todo(description);
    }

    /**
     * Types of input
     * Deadline: (eg. deadline return book /by Sunday)
     * Event: (eg. event project meeting /from Mon 2pm /to 4pm)
     * @return
     * @throws InvalidFormatException
     * @throws InvalidCommandException
     */
    public Task createTask() throws InvalidFormatException, InvalidCommandException {
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
