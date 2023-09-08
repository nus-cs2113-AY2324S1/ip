public class Deadline extends Todo {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        isDone = false;
        type = "D";
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}