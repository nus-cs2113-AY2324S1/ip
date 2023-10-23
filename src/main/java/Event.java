/**
 * Event is an extension of the Task object. Use to schedule a task between an x and y time.
 * @author Isaiah Cerven
 * @version 1.0
 */
public class Event extends Task{

	protected String time;
	String start;
	String end;
	public Event(String description, String start, String end) {
		super(description);
		this.start = start;
		this.end = end;
	}

	// getDescription prints human-readable tasks when called.
	@Override
	public String getDescription() {
		return "[E]" + super.getDescription()  + " " + start + "-" + end ;
	}

	//getFileReadableString() prints tasks and their parameters. Separates them by a '/' to be parsed in txt file.
	@Override
	public String getFileReadableString(){
		return "E" + super.getFileReadableString()  + "/" + start + "/" + end ;
	}
}
