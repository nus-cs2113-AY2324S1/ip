package Parser;

public class Parser {
    
    protected String command;
    protected String arguments;

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

    public String[] getEventArguments() throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        checkDescription(arguments);
        String[] eventArguments = new String[3];
        String[] argumentsList = arguments.split("/from");
        String description = argumentsList[0].trim().replace(",", "");
        argumentsList = argumentsList[1].split("/to");
        String from = argumentsList[0].trim().replace(",", "");
        String to = argumentsList[1].trim().replace(",", "");
        checkEventArgs(description, from, to);
        eventArguments[0] = description;
        eventArguments[1] = from;
        eventArguments[2] = to;
        return eventArguments;
    }

    public String[] getDeadlineArguments() throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        checkDescription(arguments);
        String[] deadlineArguments = new String[2];
        String[] argumentsList = arguments.split("/by");
        String description = argumentsList[0].trim().replace(",", "");
        String by = argumentsList[1].trim().replace(",", "");
        checkDeadlineArgs(description, by);
        deadlineArguments[0] = description;
        deadlineArguments[1] = by;
        return deadlineArguments;
    }

    public String getToDoArguments() throws IllegalArgumentException {
        checkDescription(arguments);
        return arguments.replace(",", "");
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
