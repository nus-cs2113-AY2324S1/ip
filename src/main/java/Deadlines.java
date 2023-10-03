public class Deadlines extends Task {
    String by;
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSave() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription()
                + " | " + this.getBy();
    }
}
