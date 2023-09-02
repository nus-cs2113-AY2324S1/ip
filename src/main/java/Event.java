public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
