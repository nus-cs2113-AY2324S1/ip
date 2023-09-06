public class Todo extends Task{
	protected String by;

	public Todo(String description) {
		super(description);
	}

	@Override
	public String getDescription() {
		return "[T]" + super.getDescription();
	}
}
