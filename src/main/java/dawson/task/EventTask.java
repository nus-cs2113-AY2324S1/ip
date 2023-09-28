package dawson.task;

public class EventTask extends Task {

    protected String start;
    protected String end;

	public EventTask(String description, String start, String end) {
		super(description);
        this.start = start;
        this.end = end;
	}

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), start, end);
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", isDoneString, description, start, end);
    }
    
}