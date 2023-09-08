package task;

public class Deadline extends Task {
	protected String due;
	
	public Deadline(String description, String due) {
		super(description);
		this.due = due;
	}
	
	public String getDue() {
		return due;
	}
	
	public String toString() {
		return "[D]" + super.toString() + "(by: " + due + ")";
	}
}
