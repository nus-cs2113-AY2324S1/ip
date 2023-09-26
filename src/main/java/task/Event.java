package task;

/**
 * Represents an event task with an additional 'from' and 'to' variable to store task duration
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * Creates a new event task object
     *
     * @param description The details of the task
     * @param from Start of event
     * @param to End of event
     */
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
    /**
     * Override the excess information output of the task
     */
    @Override
    public String getExcess() {
        return from + " | " + to;
    }
    /**
     * Override the type of the task to 'E'
     */
    @Override
    public String getType() {
        return "E";
    }
    /**
     * Override the full detail of the task
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + from + " to: " + to + ")";
    }

}
