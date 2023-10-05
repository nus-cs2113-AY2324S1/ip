package Task;

/**
 * class indicating an instance of a event object, extending task class
 */
public class Event extends Task{
    private String startTime;
    private String endTime;

    /**
     * constructor of "Event"
     * @param taskName name of task
     * @param start start of event
     * @param end end of task
     */
    public Event(String taskName, String start, String end){
        super(taskName);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * returns string representation of event task
     * @return a string representation of the task with its status and start and end
     */
    @Override
    public String toString(){
        return "[E] " + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
