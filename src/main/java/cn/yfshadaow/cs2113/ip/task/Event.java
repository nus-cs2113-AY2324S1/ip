package cn.yfshadaow.cs2113.ip.task;

import cn.yfshadaow.cs2113.ip.command.Command;

public class Event extends Task {

    private String from;
    private String to;

    @SuppressWarnings("unused")
    public String getFrom() {
        return from;
    }

    @SuppressWarnings("unused")
    public void setFrom(String from) {
        this.from = from;
    }

    @SuppressWarnings("unused")
    public String getTo() {
        return to;
    }

    @SuppressWarnings("unused")
    public void setTo(String to) {
        this.to = to;
    }

    public Event(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStringWithIsDone() {
        return "[E][" + (isDone ? "X" : " ") + "] " + name + " (from: " + from + " to: " + to + ")";
    }

    public static Event parseEvent(Command cmd)  throws IllegalArgumentException{
        if (cmd.args.isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty!");
        }
        if (!cmd.extraArgs.containsKey("from")) {
            throw new IllegalArgumentException("Event must have -from argument!");
        }
        String fromString = cmd.extraArgs.get("from");
        if (fromString.isEmpty()) {
            throw new IllegalArgumentException("-from argument cannot be empty!");
        }
        if (!cmd.extraArgs.containsKey("to")) {
            throw new IllegalArgumentException("Event must have -to argument!");
        }
        String toString = cmd.extraArgs.get("to");
        if (toString.isEmpty()) {
            throw new IllegalArgumentException("-to argument cannot be empty!");
        }
        return new Event(String.join(" ", cmd.args), fromString, toString);
    }
}
