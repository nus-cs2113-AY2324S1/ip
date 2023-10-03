package dude;

/**
 * The `Event` class represents an event task that has a description, start time (from), and end time (to).
 * It extends the `Task` class and provides additional fields and methods specific to event tasks.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs a new `Event` task with the specified description, start time (from), and end time (to).
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "[E]";
    }

    /**
     * Converts the event task to a formatted string for saving to a file.
     *
     * @return A string representation of the event task in file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }

    /**
     * Creates an `Event` object from a string in file format.
     *
     * @param fileString A string containing the event task details in file format.
     * @return An `Event` object parsed from the file string.
     */
    public static Event fromFileFormat(String fileString) {
        String[] parts = fileString.split("\\s\\|\\s");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.setDone(event.isDone);
        }
        return event;
    }

    /**
     * Generates a string representation of the event task, including its description and time frame.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
