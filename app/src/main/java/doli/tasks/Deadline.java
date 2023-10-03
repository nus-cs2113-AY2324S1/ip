package doli.tasks;

import doli.exceptions.DoliExceptions;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/** Subclass of Task, specifying tasks containing a deadline */
public class Deadline extends Task{
    protected LocalDate deadline;
    protected final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected boolean isDone;
    public Deadline(String description, String deadlineInput) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadlineInput);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the date.");
        }
        this.isDone = false;
    }
    public void setDeadline(String deadlineInput) {
        this.deadline = LocalDate.parse(deadlineInput, DATE_FORMATTER);
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDone(boolean done) {
        this.isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        String summary = String.format("[D] %s (%s)", super.toString(), // D stands for Deadline
                "by: " + DATE_FORMATTER.format(deadline));
        return summary;
    }
}
