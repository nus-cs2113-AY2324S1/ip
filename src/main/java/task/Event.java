package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description, false);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(from, inputFormatter);
            this.from = parsedDate.format(outputFormatter);
        } catch (DateTimeParseException e) {
            this.from = from;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(to, inputFormatter);
            this.to = parsedDate.format(outputFormatter);
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    @Override
    public String getExcess() {
        return from + " | " + to;
    }

    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + from + " to: " + to + ")";
    }

}
