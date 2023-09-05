public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getCode() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dueDate + ")";
    }
}
