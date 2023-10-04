package cn.yfshadaow.cs2113.ip.task;

import cn.yfshadaow.cs2113.ip.command.Command;

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

    public static Deadline parseDeadline(Command cmd)  throws IllegalArgumentException{
        if (cmd.args.isEmpty()) {
            throw new IllegalArgumentException("Deadline name cannot be empty!");
        }
        if (!cmd.extraArgs.containsKey("by")) {
            throw new IllegalArgumentException("Deadline must have -by argument!");
        }
        String byString = cmd.extraArgs.get("by");
        if (byString.isEmpty()) {
            throw new IllegalArgumentException("-by argument cannot be empty!");
        }
        return new Deadline(String.join(" ", cmd.args), byString);
    }
}
