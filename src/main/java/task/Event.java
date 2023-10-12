package task;

/**
 * Represents a deadline task
 */
class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructor
     *
     * @param description description of the event
     * @param start starting time of the event
     * @param end ending time of the event
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.event);
        this.start = start;
        this.end = end;
    }

    /**
     * Overrides the toStirng() method in Object class
     *
     * @return string in the format of event dectiption (start end)
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append("(").append(this.start).append(this.end).append(")");
        return output.toString();
    }

    /**
     * format task to input format to be saved in storage
     * @return itself in the format of input
     */
    @Override
    public String formatAsInput() {
        return super.formatAsInput() + "/" + this.start + "/" + this.end;
    }
}
