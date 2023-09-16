package nuke.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
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

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.toString(), getFrom(), getTo());
    }

    @Override
    public String formatData() {
        return String.format("%s / %s / %s", super.formatData(), getFrom(), getTo());
    }

    public static final String TYPE = "E";
}
