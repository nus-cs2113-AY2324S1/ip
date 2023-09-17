package Duchess.TaskObjects;

public class Event extends Task{
    private String Starttime, endTime;

    public Event() {
        super();
    }

    public Event(String name, String Starttime, String endTime) {
        super(name);
        this.Starttime = Starttime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.Starttime + " to: " + this.endTime + ")";
    }

}
