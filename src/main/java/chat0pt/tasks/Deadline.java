package chat0pt.tasks;

public class Deadline extends Task {
    private String by;

    /**
     * Constructor for deadline
     * @param task Name of the task
     * @param By Deadline of the task
     */

    public Deadline(String task, String By) {
        super(task);
        this.by = By;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String toFile() {
        return "D#" + super.toFile() + "#" + by;
    }

}
