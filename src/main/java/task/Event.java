package task;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getListText() {
        return "[E] " + super.getListText() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getAddMessage() {
        return "Got it. I've added this task:\n" +
                "[E][ ] " + getName() + " (from: " + from + " to: " + to + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getDeleteMessage() {
        numberOfTasks--;
        return "Noted: I've removed this task:\n" +
                "[E][ ] " + getName() + " (from: " + from + " to: " + to + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getSaveString() {
        return "E" + super.getSaveString() + " | " + from + "-" + to;
    }

}
