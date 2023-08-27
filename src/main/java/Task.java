public class Task {
	protected String description;
	protected boolean taskDone;
	
	public Task(String description) {
		this.description = description;
		this.taskDone = false;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public boolean isDone() {
		return taskDone;
	}
	
	public void markDone() {
		taskDone = true;
		System.out.println("    ____________________________________________________________");
		System.out.println("       Nice! I've marked this task as done:");
		System.out.println("       " + this);
		System.out.println("    ____________________________________________________________");
	}
	
	public void unmarkDone() {
		taskDone = false;
		System.out.println("    ____________________________________________________________");
		System.out.println("       OK, I've marked this task as not done yet:");
		System.out.println("       " + this);
		System.out.println("    ____________________________________________________________");
	}
	
	public String toString() {
		if (taskDone)
			return "[X" + "] " + description;
		return "[ " + "] " + description;
	}
}
