public class Event extends Task{
    protected String by;
    protected String from;
    public Event(String description, String from, String by) throws DukeException{
        super(description);
        if(!from.contains("from ")){
            throw new DukeException("☹ OOPS!!! The description of a event must contain \"from\" time.");
        }else if(!by.contains("to ")){
            throw new DukeException("☹ OOPS!!! The description of a event must contain \"to\" time.");
        }
        this.from = from;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.by + ")";
    }
}
