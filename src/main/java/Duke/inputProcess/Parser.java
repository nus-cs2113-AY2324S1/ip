package duke.inputProcess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Parser` class is responsible for processing user input and extracting relevant information.
 * It parses input commands, extracts task names, and converts date and time from strings into `LocalDateTime` objects.
 */
public class Parser {
    private String taskToAdd;
    private String userInput;
    protected LocalDateTime timeGetFromText1;
    protected LocalDateTime timeGetFromText2;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a `Parser` object with the given user input.
     *
     * @param userInput The user-provided input to be processed.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the input for a deadline task and extracts the task name and deadline date and time.
     *
     * @return This `Parser` object for method chaining.
     */
    public Parser getDeadlineInput() {
        timeGetFromText1 = LocalDateTime.parse(userInput.split("/by ", 2)[1], df);
        taskToAdd = userInput.split("/", 2)[0];
        return this;
    }

    /**
     * Parses the input for an event task and extracts the task name, start date and time, and end date and time.
     *
     * @return This `Parser` object for method chaining.
     */
    public Parser getEventInput() {
        taskToAdd = userInput.split("/", 2)[0];
        String eventTime = userInput.split("/from ", 2)[1];
        timeGetFromText1 = LocalDateTime.parse(eventTime.split(" /to ", 2)[0], df);
        timeGetFromText2 = LocalDateTime.parse(eventTime.split(" /to ", 2)[1], df);
        return this;
    }

    /**
     * Get the command from user input.
     *
     * @return The command extracted in lowercase.
     */
    public String getCommand() {
        return userInput.split(" ", 2)[0].toLowerCase();
    }

    /**
     * Retrieves the remaining part of the user input after the command.
     *
     * @return The remaining part of the user input.
     */
    public String getRemainingPart() {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Updates the user input for the `Parser` object.
     *
     * @param userInput The new user input to be processed.
     */
    public void newUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Get the task name extracted from the user input.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskToAdd;
    }

    /**
     * Get the first `LocalDateTime` object extracted from the user input.
     *
     * @return The first date and time extracted from the user input.
     */
    public LocalDateTime getTaskTime1() {
        return timeGetFromText1;
    }

    /**
     * Retrieves the second `LocalDateTime` object extracted from the user input.
     *
     * @return The second date and time extracted from the user input.
     */
    public LocalDateTime getTaskTime2() {
        return timeGetFromText2;
    }
}
