package duke.deadline;

import duke.task.Task;
public class Deadline extends Task {

    protected String deadline;

    /**
     * Constructs new Deadline.
     *
     * @param description description of the task.
     * @param deadline date to complete task by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String getTask() {
        return String.format("[%s][%s] %s (by: %s)", getTypeIcon(), getStatusIcon(), description, deadline);
    }

    @Override
    public String getTaskForFile() {
        return String.format("%s | %b | %s | %s", getTypeIcon(), isDone, description, deadline);
    }
}
