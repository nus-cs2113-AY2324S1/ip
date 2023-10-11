public class Event extends Task{

	protected String time;
	String start;
	String end;
	public Event(String description, String start, String end) {
		super(description);
		this.start = start;
		this.end = end;
	}

	@Override
	public String getDescription() {
		return "[E]" + super.getDescription()  + " " + start + "-" + end ;
	}

	@Override
	public String getFileReadableString(){
		return "E" + super.getFileReadableString()  + "/" + start + "/" + end ;
	}
}
