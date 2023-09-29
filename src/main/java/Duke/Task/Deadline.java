package Duke.Task;

/**
 * A Specific type of task that contains a task description and an end date.
 */
public class Deadline extends Task {
    protected String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
        taskType = "Deadline";
        symbol = "D";
    }

    /**
     * Encodes Deadline data for file saving.
     * @return Encoded Deadline data in String format.
     */
    @Override
    public String convertToSaveFormat() {
        String doneMarker = isDone ? "1" : "0";
        return symbol + " | " + doneMarker + " | " + description + "/by " + endDate;
    }

    /**
     * Override the printing of Deadline.
     * @return String format of Deadline for printing.
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: "
                + this.endDate + ")";
    }

}
