/**
 * Task stores the users task description and done status.Specific Task types are extended from this class.
 * @author Isaiah Cerven
 * @version 1.0
 */
public class Task {
	String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}
	// Mark tasks as done
	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	public void setDone(boolean done) {
		isDone = done;
	}

	// getDescription prints human-readable tasks when called.
	public String getDescription() {
		return "[" + getStatusIcon() + "] " + description;
	}

	public String getFileDescriptionStatus(){
		return "/" + getStatusIcon() + "/" + description;
	}

	//getFileReadableString() prints tasks and their parameters. Separates them by a '/' to be parsed in txt file.
	public String getFileReadableString(){
		//This is in place of the normal getDescription that is returned with the List command
		return "/" + getStatusIcon() + "/" + description;
	}




}
