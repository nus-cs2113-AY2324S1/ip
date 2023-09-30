package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task{
    protected LocalDateTime by;
    private Parser parser;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        parser = new Parser();
    }

    public Deadline(String description, String isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
        parser = new Parser();
    }

    @Override
    public String toString() {
        String byString = parser.convertDateTimetoString(by);
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
