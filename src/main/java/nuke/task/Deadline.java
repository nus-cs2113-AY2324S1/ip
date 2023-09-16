package nuke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        setBy(by);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
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
