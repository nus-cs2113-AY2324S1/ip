public class Event extends Task {
    String from;
    String to;

    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns the format of the task to be printed out to user
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    /**
     * Returns the format of the task to be saved into a file
     * @return String representation of the task
     */
    @Override
    public String toSave() {
        return "E | " + super.toSave() + " | " + from + " | " + to;
    }
}

