package rene.task;
public class Event extends Task{
    private String startTime;
    private String endTime;
    public Event(String description, String startTime, String endTime){
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskTiming() {
        return "(from: " + startTime + " to: " + endTime + ")";
    }
}
