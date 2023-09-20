package cn.yfshadaow.cs2113.ip.task;

import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task {
    private String by;
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }


    public Deadline(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public String toStringWithIsDone() {
        return "[D][" + (isDone ? "X" : " ") + "] " + name + " (by: " + by + ")";
    }

    public static Deadline parseDeadline(String[] args) {
        int index = 0;
        List<String> nameFragments = new ArrayList<>();
        while (!args[index].equals("/by")) {
            nameFragments.add(args[index]);
            index += 1;
        }
        index += 1;
        List<String> byFragments = new ArrayList<>();
        while (index < args.length) {
            byFragments.add(args[index]);
            index += 1;
        }
        return new Deadline(String.join(" ", nameFragments), String.join(" ", byFragments));
    }
}
