public class Event extends Task{

    public Event(String description, String from, String to) {
        super(description);
        super.taskType = "event";
        super.from = from;
        super.to = to;
    }
}
