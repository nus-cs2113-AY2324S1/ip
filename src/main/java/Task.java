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
		System.out.println("    ____________________________________________________________");
		if (status) {
			done = true;
			System.out.println("     Nice! I've marked this task as done:");
		} else {
			done = false;
			System.out.println("     OK, I've marked this task as not done yet:");
		}
		System.out.println("       " + this);
		System.out.println("    ____________________________________________________________");
	}
	
	public String toString() {
		if (this.isDone()) {
			return "[X] " + description;
		}
		return "[ ] " + description;
	}
}
