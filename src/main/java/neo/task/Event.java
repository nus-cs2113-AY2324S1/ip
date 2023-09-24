package neo.task;

public class Event extends Task {

    protected String from;
    protected String to;
    protected String formattedFrom;
    protected String formattedTo;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        if (hasTime(from)) {
            formattedFrom = formatDateAndTime(from);
        } else {
            formattedFrom = formatDate(from);
        }
        if (hasTime(to)) {
            formattedTo = formatDateAndTime(to);
        } else {
            formattedTo = formatDate(to);
        }
    }

    @Override
    public String formatTask() {
        return "E / " + super.formatTask() + " / " + from + " / " + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
