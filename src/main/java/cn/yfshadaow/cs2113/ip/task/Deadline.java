package cn.yfshadaow.cs2113.ip.task;

import cn.yfshadaow.cs2113.ip.command.Command;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Gets by.
     *
     * @return the by
     */
    @SuppressWarnings("unused")
    public String getBy() {
        return by;
    }

    /**
     * Sets by.
     *
     * @param by the by
     */
    @SuppressWarnings("unused")
    public void setBy(String by) {
        this.by = by;
    }


    /**
     * Instantiates a new Deadline.
     *
     * @param name task name
     * @param by   the by attribute
     */
    public Deadline(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public String toStringWithIsDone() {
        return "[D][" + (isDone ? "X" : " ") + "] " + name + " (by: " + by + ")";
    }

    /**
     * Parse deadline from command.
     *
     * @param cmd the cmd
     * @return the deadline
     * @throws IllegalArgumentException the illegal argument exception
     */
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
