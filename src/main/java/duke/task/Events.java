package duke.task;

public class Events extends Task{
    private String from;
    private String end;

    public Events(String taskName, String from, String end) {
        super(taskName);
        this.from = from;
        this.end = end;
    }

    public void UpdateDate(String newFrom, String newEnd) {
        this.from = newFrom;
        this.end = newEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.end + ")";
    }
}
