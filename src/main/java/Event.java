import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  Event class
 *  extends Task class
 *  tasks with start and end time
 */
public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to){
        super(description);
        this.from = from;
        this.to = to;
    }

    //override
    public String getTypeIcon(){
        return "E";
    }

    //override
    public String getDescription(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);
        return String.format("%s (from: %s to: %s)", description, from.format(formatter), to.format(formatter));
    }
}
