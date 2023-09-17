package task;

public class Task {
	protected String description;
	protected boolean done;
	
	public Task(String description) {
		this.description = description;
		this.done = false;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean status) {
		if (status) {
			done = true;
		} else {
			done = false;
		}
	}
	
	public String toString() {
		if (this.isDone()) {
			return "[X] " + description;
		}
		return "[ ] " + description;
	}
}
