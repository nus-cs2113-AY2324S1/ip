package Tasks;

public class Event extends Task{
    protected String time;
    protected String startTime;
    protected String endTime;

    public Event(String description, String time) {
        super(description);
        this.time = time;
        String[] timeParts = time.split(" to ", 2);
        this.startTime = timeParts[0];
        this.endTime = timeParts[1];
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + time + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
