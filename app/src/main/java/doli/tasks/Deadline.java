package doli.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h3>Deadline class</h3>
 * The Deadline class extends the Task class and is specifically
 * made to handle tasks of type deadline, meaning that they have a due date.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class Deadline extends Task{
    /** Date indicating the deadline pertaining to the task */
    protected LocalDate deadline;
    /** Format for the deadline date to be returned */
    protected final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    /** Error message in case the parsing of the date was unsuccessful */
    protected final String DATE_PARSING_FAILED = "Could not parse the date.";

    /**
     * Constructs an object of type Deadline with a description of the task
     * and a deadline by which the task is due.
     *
     * @param description of type String referring to details about the deadline.
     * @param deadlineInput of type String ("yyyy-MM-dd") to be parsed into type LocalDate for further use.
     */
    public Deadline(String description, String deadlineInput) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadlineInput);
        } catch(DateTimeException e) {
            System.out.println(DATE_PARSING_FAILED);
        }
    }

    /**
     * Sets a new deadline date for the task.
     *
     * @param deadlineInput of type String and format "yyyy-MM-dd".
     */
    public void setDeadline(String deadlineInput) {
        this.deadline = LocalDate.parse(deadlineInput, DATE_FORMATTER);
    }

    /**
     * Gets the deadline date of the task.
     *
     * @return deadline date as type LocalDate in format "yyyy-MM-dd".
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Changes the boolean variable isDone of the task.
     *
     * @param isDone of type boolean.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns whether a deadline task was completed or not.
     *
     * @return boolean variable explaining whether the task was completed or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Overrides the superclass' method .toString() allowing for
     * a tailored use incorporating details of the task such as its type and whether it is done or not.
     *
     * @return a String summarising the deadline's details.
     */
    @Override
    public String toString() {
        String summary = String.format("[D] %s (%s)", super.toString(),
                "by: " + DATE_FORMATTER.format(deadline));
        return summary;
    }
}
