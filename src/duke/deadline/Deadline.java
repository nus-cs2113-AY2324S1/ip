package duke.deadline;

import duke.task.Task;
public class Deadline extends Task {

    protected String deadline;

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
}
