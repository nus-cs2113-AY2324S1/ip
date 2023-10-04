public class Deadline extends Task {
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the format of the task to be printed out to user
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the format of the task to be saved into a file
     * @return String representation of the task
     */
    @Override
    public String toSave() {
        return "D | " + super.toSave() + " | " + by;
    }
}
