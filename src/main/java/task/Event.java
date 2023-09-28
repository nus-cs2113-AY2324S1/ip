package task;

class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description, TaskType.event);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append("(").append(this.start).append(this.end).append(")");
        return output.toString();
    }

    // format task to input format to be saved in storage
    @Override
    public String formatAsInput() {
        return super.formatAsInput() + "/" + this.start + "/" + this.end;
    }
}
