package tasks;

import exceptions.TaskEmptyDescriptionException;
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, String deadlineString) throws TaskEmptyDescriptionException {
        super(description);
        this.deadline = LocalDate.parse(deadlineString);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadlineString) {
        this.deadline = LocalDate.parse(deadlineString);
    }

    @Override
    public void printTask() {
        System.out.println("\t[D]" + getCompletedString() + getDescription() + " (by: " + getDeadline() + ")");
    }
}
