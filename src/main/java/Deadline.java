/**
 * Deadline is an extension of the Task object. Use to denote when something is due.
 * @author Isaiah Cerven
 * @version 1.0
 */

public class Deadline extends Task {

	protected String by;


	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	// getDescription prints human-readable tasks when called.
	@Override
	public String getDescription() {
		return "[D]" + super.getDescription() + " (by: " + by + ")";
	}

	//getFileReadableString() prints tasks and their parameters. Separates them by a '/' to be parsed in txt file.
	@Override
	public String getFileReadableString(){
		return "D" + super.getFileReadableString() + "/" + by;
	}


}
