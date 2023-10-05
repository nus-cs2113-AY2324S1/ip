package python.task;

public class Deadline extends Task {
    protected String by;

    final static public String TYPE_ICON = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String toDiskSaveFormat() {
        return super.toDiskSaveFormat() + " | " + getBy();
    }
}
