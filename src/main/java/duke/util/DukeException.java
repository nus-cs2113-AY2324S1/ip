package main.java.duke.util;

public class DukeException extends Exception{

    private String message;
    public DukeException(String error) {
        super();
        if(error.equals("Empty List"))
            this.message = "No item stored in your list! :o";
        if(error.equals("Todo Name Missing"))
            this.message = "Todo name cannot be empty! :<";
        if(error.equals("Event Name Missing"))
            this.message = "Event name cannot be empty! :<";
        if(error.equals("Event From Missing"))
            this.message = "Event from date cannot be empty! :<";
        if(error.equals("Event To Missing"))
            this.message = "Event to date cannot be empty! :<";
        if(error.equals("Deadline Name Missing"))
            this.message = "Deadline name cannot be empty! :<";
        if(error.equals("Deadline By Missing"))
            this.message = "Deadline by date cannot be empty! :<";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
