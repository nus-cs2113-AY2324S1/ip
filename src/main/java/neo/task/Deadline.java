package neo.task;

public class Deadline extends Task {

    protected String by;
    protected String formattedBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        if (hasTime(by)) {
            formattedBy = formatDateAndTime(by);
        } else {
            formattedBy = formatDate(by);
        }
    }
    @Override
    public String formatTask() {
        return "D / " + super.formatTask() + " / " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
