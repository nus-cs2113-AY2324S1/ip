public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark(){
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T][" + getStatusIcon() + "] " + super.getDescription();
    }
}

class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getDescription() {
        return "[D][" + getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;


    public Event(String description, String from, String to) {
        super(description);
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
    public String getDescription() {
        return "[E][" + getStatusIcon() + "] " + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}