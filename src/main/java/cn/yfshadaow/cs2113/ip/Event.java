package cn.yfshadaow.cs2113.ip;

import java.util.ArrayList;
import java.util.List;

public class Event extends Task {

    private String from;
    private String to;

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

    public Event(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStringWithIsDone() {
        return "[E][" + (isDone ? "X" : " ") + "] " + name + " (from: " + from + " to: " + to + ")";
    }

    public static Event parseEvent(String[] args) {
        int index = 0;
        List<String> nameFragments = new ArrayList<>();
        while (!args[index].equals("/from")) {
            nameFragments.add(args[index]);
            index += 1;
        }
        index += 1;
        List<String> fromFragments = new ArrayList<>();
        while (!args[index].equals("/to")) {
            fromFragments.add(args[index]);
            index += 1;
        }
        index += 1;
        List<String> toFragments = new ArrayList<>();
        while (index < args.length) {
            toFragments.add(args[index]);
            index += 1;
        }
        return new Event(String.join(" ", nameFragments), String.join(" ", fromFragments), String.join(" ", toFragments));
    }
}
