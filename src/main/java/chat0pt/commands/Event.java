package chat0pt.commands;

import chat0pt.commands.Task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String tasks, String from, String to) {
        super(tasks);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from:" + from + " to:" + to +")";
    }
}
