package Tasks;

import Exceptions.TaskEmptyDescriptionException;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) throws TaskEmptyDescriptionException {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public void printTask() {
        System.out.println("\t[D]" + getCompletedString() + getDescription() + " (by: " + getDeadline() + ")");
    }
}
