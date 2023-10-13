package task;

/**
 * Represents a deadline task
 */
class Deadline extends Task {
    protected String by;

    /**
     * Constructor
     *
     * @param description deadline task description
     * @param by due date/time
     */
    public Deadline(String description, String by) {
        super(description, TaskType.deadline);
        this.by = by;
    }

    /**
     * overrides the toString() method in Object class
     *
     * @return string in the format of description (by: due date)
     */
    @Override
    public String toString() {
        return super.toString() + " (by:" + by + ")";
    }

    /**
     * format task to input format to be saved in storage
     * @return itself in the format of input
     */
    @Override
    public String formatAsInput() {
        return super.formatAsInput() + "/by" + this.by;
    }
}
