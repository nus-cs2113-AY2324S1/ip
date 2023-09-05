public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String taskName, String start, String end){
        super(taskName);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + "(from: " + startTime + " to: " + endTime + ")";
    }
}
