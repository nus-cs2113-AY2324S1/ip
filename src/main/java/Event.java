public class Event extends Task{
    private String from;
    private String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
        } else {
            return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
        }
    }
}
