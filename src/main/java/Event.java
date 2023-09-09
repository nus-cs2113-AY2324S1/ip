/**
 *  Event class
 *  extends Task class
 *  tasks with start and end time
 */
public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    //override
    public String getTypeIcon(){ return "E"; }

    //override
    public String getDescription(){
        return String.format("%s (from: %s to: %s)", description, from, to);
    }
}
