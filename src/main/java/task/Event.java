package task;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getListText() {
        return "[E] " + super.getListText() +
                " (from: " + parseDate(from) +
                " to: " + parseDate(to) + ")";
    }

    @Override
    public String getAddMessage() {
        return "Got it. I've added this task:\n" +
                "[E][" + (getIsComplete() ? "X" : " ") + "] " + getName() +
                " (from: " + parseDate(from) + " to: " + parseDate(to) + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getDeleteMessage() {
        numberOfTasks--;
        return "Noted: I've removed this task:\n" +
                "[E][" + (getIsComplete() ? "X" : " ") + "] " + getName() +
                " (from: " + parseDate(from) + " to: " + parseDate(to) + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getSaveString() {
        return "E" + super.getSaveString() + " | " + from + "_" + to;
    }

}
