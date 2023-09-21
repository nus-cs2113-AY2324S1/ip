package nuke.task;

import nuke.NukeDateTime;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private NukeDateTime by;

    public Deadline(String name, String by) {
        super(name);
        setBy(by);
    }

    public String getBy() {
        return by.toString();
    }

    public void setBy(String by) {
        this.by = new NukeDateTime(by);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getBy());
    }

    @Override
    public String formatData() {
        return String.format("%s / %s", super.formatData(), getBy());
    }

    public static final String TYPE = "D";
}
