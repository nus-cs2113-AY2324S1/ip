package Chatty.tasks;

public abstract class Task {
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
    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }
    public boolean getIsDone(){
        return isDone;
    }
    public String saveFormat() {
        return "";
    }
}



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