package neo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a form of task. This class is abstract as tasks are always constructed as
 * a form of Todo, Deadline or Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Sets the status of task to be done or not done.
     *
     * @param done Indicates what status to set the task as.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Constructs a task with a string to describe it. Default value of whether
     * the task is done is false.
     *
     * @param description This is the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private boolean hasTime(String line) {
        String[] dateAndTime = line.split(" ");
        return dateAndTime.length == 2;
    }

    /**
     * Formats the string representing date or date and time into a format of
     * (Day of Week), (Day) (Full name of Month) (Year) HH:mm AM/PM. If there is no time,
     * the time portion of the format will be ignored.
     * For example: 25/09/2023 1300 -> Monday, 25 September 2023, 1:00 PM.
     *
     * @param line This is the string representing the date or date and time.
     * @return The formatted date or date and time.
     */
    public String dateTimeFormatter(String line) {
        if (hasTime(line)) {
            return formatDateAndTime(line);
        } else {
            return formatDate(line);
        }
    }
    private String formatDate(String line) {
        LocalDate date = LocalDate.parse(line, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    private String formatDateAndTime(String line) {
        LocalDateTime dateTime = LocalDateTime.parse(line, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT));
    }

    /**
     * Returns the task in a format for saving into a .txt file.
     *
     * @return The formatted task.
     */
    public String formatTask() {
        int status;
        if (this.getStatusIcon().equals("X")) {
            status = 1;
        } else {
            status = 0;
        }
        return status + " / " + this.description;
    }

    /**
     * Returns the task in an easy-to-read format to the user.
     *
     * @return The formatted task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
