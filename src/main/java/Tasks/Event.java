package Tasks;

public class Event extends Task{
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + time + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
