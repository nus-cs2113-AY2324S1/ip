public class Task {
	String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}
	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	public void setDone(boolean done) {
		isDone = done;
	}


	public String getDescription() {
		return "[" + getStatusIcon() + "] " + description;
	}




}
