/**
 *  Deadline class
 *  extends Task class
 *  tasks with deadline
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task{
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    //override
    public String getTypeIcon(){
        return "D";
    }

    //override
    public String getDescription(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);
        // delete "(by:" and ")"
        return String.format("%s (by: %s)", description, deadline.format(formatter));
    }
}
