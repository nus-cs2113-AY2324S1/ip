package sun.task;
public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + dueDate + ")";
    }
}

