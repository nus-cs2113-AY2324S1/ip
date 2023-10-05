package torchie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private final String DATE_TIME_FORMAT = "MMM d yyyy, HH:mm";

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Convert LocalDateTime object to user-friendly String
     *
     * @param d LocalDateTime object to be converted
     * @return String that shows a user-friendly version of DateTime
     *
     */
    public String formatOutput(LocalDateTime d) {
        return d.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    /**
     * Used to display Event object in a user-friendly way
     *
     * @return String that shows a user-friendly version of Event object
     *
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + formatOutput(startTime) + " to: " + formatOutput(endTime) + ")");
    }

    /**
     * Print a line to tell users that task was added successfully
     *
     */
    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask(this.toString());
    }

    /**
     * Change Event task to a format that will be used to store in file
     *
     * @return String raw format to be saved in file
     *
     */
    @Override
    public String toFileFormat() {
        return ("E | " + super.toFileFormat() + " | /from " + getStartTime() + " /to " + getEndTime());
    }

    /**
     * Print a line to tell users that task was deleted successfully
     *
     */
    public void announceTaskDelete() {
        super.announceTaskDelete();
        super.printTask(this.toString());
    }
}
