/**
 * Todo is an extension of the Task object. Use to make task and due date.
 * @author Isaiah Cerven
 * @version 1.0
 */
public class Todo extends Task{
	protected String by;

	public Todo(String description) {
		super(description);
	}

	@Override
	public String getDescription() {
		return "[T]" + super.getDescription();
	}

	@Override
	public String getFileReadableString(){
		return "T" + super.getFileReadableString();
	}
}
