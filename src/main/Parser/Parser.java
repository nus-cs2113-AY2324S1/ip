package Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    
    protected String command;
    protected String arguments;
    protected String description;
    protected LocalDate by;
    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM uuuu");

    public Parser() {
        this.command = "";
        this.arguments = "";
    }

    public void convertInput(String userInput) {
        this.arguments = "";
        this.command = userInput.split(" ", 2)[0];
        if(userInput.split(" ", 2).length != 1) {
            this.arguments = userInput.split(" ", 2)[1];
        }
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

    public LocalDate getBy() {
        return by;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
    
    public void checkFrom(String from) {
        if(from.isBlank()) {
            throw new IllegalArgumentException("From Blank");
        }
    }

    public void checkDescription(String description) {
        if(description.isBlank()) {
            throw new IllegalArgumentException("Description Blank");
        }
    }

    public void checkTo(String to) {
        if(to.isBlank()) {
            throw new IllegalArgumentException("To Blank");
        }
    }

    public void checkEventArgs(String description, String from, String to) {
        checkDescription(description);
        checkFrom(from);
        checkTo(to);
    }

    public void checkBy(String by) {
        if(by.isBlank()) {
            throw new IllegalArgumentException("By Blank");
        }
    }

    public void checkDeadlineArgs(String description, String by) {
        if(description.isBlank() && by.isBlank()) {
            throw new ArrayIndexOutOfBoundsException(0);
        }
        checkDescription(description);
        checkBy(by);
    }

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

    public void setDeadlineArguments() throws ArrayIndexOutOfBoundsException, IllegalArgumentException, DateTimeParseException {
        checkDescription(arguments);
        String[] argumentsList = arguments.split("/by");
        this.description = argumentsList[0].trim().replace(",", "");
        String by = argumentsList[1].trim().replace(",", "");
        checkDeadlineArgs(description, by);
        checkDeadlineDate(by);
    }

    public void setToDoArguments() throws IllegalArgumentException {
        checkDescription(arguments);
        this.description = arguments.replace(",", "");
    }

    public int getMarkListArguments() throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(arguments) - 1;
        return index;
    }

    public int getDeleteArguments() throws IndexOutOfBoundsException, IllegalArgumentException {
        int index = Integer.parseInt(arguments) - 1;
        return index;
    }

    public void checkKeyword(String keyword) {
        if(keyword.isBlank() || keyword.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public String getFindArguments() throws IllegalArgumentException {
        checkKeyword(arguments);
        return arguments;
    }
}
