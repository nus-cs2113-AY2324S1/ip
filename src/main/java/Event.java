public class Event extends Task {
    protected String start, end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }


    @Override
    public String toString() {
        //print example: [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
        return "[E]" + super.toString() + " (from: " + this.start +  " to: " + this.end + ")";
    }

}
