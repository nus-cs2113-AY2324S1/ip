package dawson.task;

public class DeadlineTask extends Task {

    protected String by;

	public DeadlineTask(String description, String by) {
		super(description);
        this.by = by;
	}

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", isDoneString, description, by);
    }
    
}
