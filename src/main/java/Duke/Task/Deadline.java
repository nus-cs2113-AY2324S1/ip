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

    @Override
    public String convertToSaveFormat() {
        String doneMarker = isDone ? "1" : "0";
        return symbol + " | " + doneMarker + " | " + description + "/by " + endDate;
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + this.endDate + ")";
    }

}
