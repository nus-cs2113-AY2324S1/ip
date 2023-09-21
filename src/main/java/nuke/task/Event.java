package nuke.task;

import nuke.NukeDateTime;

public class Event extends Task {
    private NukeDateTime from;
    private NukeDateTime to;

    public Event(String name, String from, String to) {
        super(name);
        setFrom(from);
        setTo(to);
    }

    public String getFrom() {
        return from.toString();
    }

    public void setFrom(String from) {
        this.from = new NukeDateTime(from);
    }

    public String getTo() {
        return to.toString();
    }

    public void setTo(String to) {
        this.to = new NukeDateTime(to);
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
