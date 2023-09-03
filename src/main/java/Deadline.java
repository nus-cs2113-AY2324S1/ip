public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        String deadline = super.toString() + " (by: " + by + ")";
        if (super.getDone()) {
            return "[D][X] " + deadline;
        } else {
            return "[D][ ] " + deadline;
        }
    }
}