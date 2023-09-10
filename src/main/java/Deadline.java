public class Deadline extends Task {

    protected String byDeadline;

    public Deadline(String description, String by) {
        super(description);
        this.byDeadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDeadline + ")";
    }
}
