public class Events extends Task {
    String from;
    String to;

    public Events(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
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
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    @Override
    public String toSave() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription()
                + " | " + this.getFrom() + " | " + this.getTo();
    }
}

