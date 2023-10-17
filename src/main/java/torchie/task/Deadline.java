package torchie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private final String DATE_TIME_FORMAT = "MMM d yyyy, HH:mm";

    public Deadline(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
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
     * Used to display Deadline object in a user-friendly way
     *
     * @return String that shows a user-friendly version of Deadline object
     *
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + formatOutput(deadline) + ")");
    }

    /**
     * Print a line to tell users that task was added successfully
     *
     */
    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        super.printTask(this.toString());
    }

    /**
     * Change Deadline task to a format that will be used to store in file
     *
     * @return String raw format to be saved in file
     *
     */
    @Override
    public String toFileFormat() {
        return ("D | " + super.toFileFormat() + " | /by " + getDeadline());
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