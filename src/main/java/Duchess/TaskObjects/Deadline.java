package Duchess.TaskObjects;

import Duchess.FunctionObjects.DateParser;

public class Deadline extends Task {

    private String deadline;

    public Deadline() {
        super();
    }

    public Deadline(String name, String deadline) {
        super(name);
        DateParser parsedDeadline = new DateParser(deadline);
        if (parsedDeadline.getDate() == null) {
            this.deadline = deadline;
        } else {
            this.deadline = parsedDeadline.getDate() + " " + parsedDeadline.getTime();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.deadline;
    }
}    