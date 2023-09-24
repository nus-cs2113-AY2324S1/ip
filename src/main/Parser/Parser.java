package Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    
    protected String command;
    protected String arguments;
    protected String description;
    protected String keyword;
    protected LocalDate by;
    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM uuuu");

    public Parser() {
        this.command = "";
        this.arguments = "";
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyword() {
        return keyword;
    }

    public LocalDate getBy() {
        return by;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    /**
     * Converts user input and stores it as a command and an arguments respectively. 
     * 
     * @param userInput The user input that is taken from the user.
     */
    public void convertInput(String userInput) {
        this.arguments = "";
        this.command = userInput.split(" ", 2)[0];
        if(userInput.split(" ", 2).length != 1) {
            this.arguments = userInput.split(" ", 2)[1];
        }
    }

    /**
     * Checks if the from variable is a blank string and throws a IllegalArgumentException if it is blank.
     * 
     * @param from The from variable to be stored in the object.
     */
    public void checkFrom(String from) {
        if(from.isBlank()) {
            throw new IllegalArgumentException("From Blank");
        }
    }

    /**
     * Checks if the description variable is a blank string and throws a IllegalArgumentException if it is blank.
     * 
     * @param description The description variable to be stored in the object.
     */
    public void checkDescription(String description) {
        if(description.isBlank()) {
            throw new IllegalArgumentException("Description Blank");
        }
    }


    /**
     * Checks if the description variable is a blank string and throws a IllegalArgumentException if it is blank.
     * 
     * @param to The to variable to be stored in the object.
     */
    public void checkTo(String to) {
        if(to.isBlank()) {
            throw new IllegalArgumentException("To Blank");
        }
    }

    /**
     * Calls the function to check whether description, from and to variables is blank.
     * 
     * @param description The description variable to be stored in the object.
     * @param from The from variable to be stored in the object.
     * @param to The to variable to be stored in the object.
     */
    public void checkEventArgs(String description, String from, String to) {
        checkDescription(description);
        checkFrom(from);
        checkTo(to);
    }

    /**
     * Checks if the by variable is a blank string and throws a IllegalArgumentException if it is blank.
     * 
     * @param by The by variable to be stored in the object.
     */
    public void checkBy(String by) {
        if(by.isBlank()) {
            throw new IllegalArgumentException("By Blank");
        }
    }

    /**
     * Calls the function to check whether description and by variables is blank.
     * 
     * @param description The description variable to be stored in the object.
     * @param by The by variable to be stored in the object.
     */
    public void checkDeadlineArgs(String description, String by) {
        checkDescription(description);
        checkBy(by);
    }

    /**
     * Converts from and to variables into LocalDate type.
     * 
     * @param from From date in string format.
     * @param to To date in string format.
     */
    public void checkEventDate(String from, String to) {
        this.from = LocalDate.parse(from, FORMATTER);
        this.to = LocalDate.parse(to, FORMATTER);
        if(this.to.isBefore(this.from)) {
            throw new DateTimeParseException("To before from", to, 0);
        }
    }

    public void checkDeadlineDate(String by) {
        this.by = LocalDate.parse(by, FORMATTER);
    }

    /**
     * Sets the arguments to be used for creating an Event object.
     * 
     * @throws ArrayIndexOutOfBoundsException If arguments does not contains the proper format.
     * @throws IllegalArgumentException If description, from, to is blank string.
     * @throws DateTimeParseException If from and to cannot be converted to LocalDate type or to is before from.
     */
    public void setEventArguments() throws ArrayIndexOutOfBoundsException, IllegalArgumentException, DateTimeParseException {
        checkDescription(arguments);
        String[] argumentsList = arguments.split("/from");
        this.description = argumentsList[0].trim().replace(",", "");
        argumentsList = argumentsList[1].split("/to");
        String from = argumentsList[0].trim().replace(",", "");
        String to = argumentsList[1].trim().replace(",", "");
        checkEventArgs(description, from, to);
        checkEventDate(from, to);
    }

    /**
     * Sets the arguments to be used for creating a Deadline object.
     * 
     * @throws ArrayIndexOutOfBoundsException If arguments does not contain the proper format.
     * @throws IllegalArgumentException If description and by is blank string.
     * @throws DateTimeParseException If by cannot be converted to LocalDate type.
     */
    public void setDeadlineArguments() throws ArrayIndexOutOfBoundsException, IllegalArgumentException, DateTimeParseException {
        checkDescription(arguments);
        String[] argumentsList = arguments.split("/by");
        this.description = argumentsList[0].trim().replace(",", "");
        String by = argumentsList[1].trim().replace(",", "");
        checkDeadlineArgs(description, by);
        checkDeadlineDate(by);
    }

    /**
     * Sets the arguments to be used for creating a ToDo object.
     * 
     * @throws IllegalArgumentException If arguments is blank.
     */
    public void setToDoArguments() throws IllegalArgumentException {
        checkDescription(arguments);
        this.description = arguments.replace(",", "");
    }

    /**
     * Gets the index of the task in the task list.
     * 
     * @return Index of the object.
     * @throws NumberFormatException If arguments cannot be converted into an Integer type.
     */
    public int getIndex() throws NumberFormatException {
        int index = Integer.parseInt(arguments) - 1;
        return index;
    }

    /**
     * Checks if the keyword variable is blank or empty and throws an IllegalArgumentException error if it is not.
     * 
     * @param keyword Keyword to be searched in the task list.
     */
    public void checkKeyword(String keyword) {
        if(keyword.isBlank() || keyword.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the arguments to be used for the find command.
     * 
     * @throws IllegalArgumentException If arguments is blank or empty.
     */
    public void setFindArguments() throws IllegalArgumentException {
        checkKeyword(arguments);
        this.keyword = arguments;
    }
}