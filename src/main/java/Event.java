public class Event extends Task {
    private String from;
    private String to;

    public static Event parseEvent(String cmdBody) {
        String[] args = cmdBody.split(" /from | /to ");
        String name = args[0];
        String from = args[1];
        String to = args[2];

        return new Event(name, from, to);
    }

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), getFrom(), getTo());
    }
}
