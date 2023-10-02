package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * A general class for all tasks that Duke accepts.
 */
public abstract class Task {

    private boolean isComplete;
    private String name;

    protected static int numberOfTasks = 0;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
        numberOfTasks++;
    }

    public String getName() {
        return name;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsComplete(boolean complete) {
        isComplete = complete;
    }

    public String getListText() {
        return "[" + (getIsComplete() ? "X" : " ") + "] " + getName();
    }

    public String getSaveString() {
        return " | " + (getIsComplete() ? "1" : "0") + " | " + getName();
    }

    protected String parseDate(String time) {
        try {
            return LocalDate.parse(time).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return time;
        }
    }

    public abstract String getAddMessage();
    public abstract String getDeleteMessage();
}
