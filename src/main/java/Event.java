public class Event extends Deadline {

    protected String from;

    public Event(String name, String by, String from) {
        super(name, by);
        this.from = from;
    }

    @Override
    public String toString() {
        if (super.getDone()) {
            return "[E][X] " + super.getName();
        } else {
            return "[E][ ] " + super.getName();
        }
    }
}
