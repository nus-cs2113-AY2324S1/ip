package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime dueDate;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    public Deadline(String description, LocalDateTime dueDate){
        super(description);
        this.dueDate = dueDate;

    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + dueDate.format(DTF) + ")";
    }

    @Override
    public String toFileString() {
        return ("D | " + super.toFileString() + " | " + dueDate.format(DTF));
    }
}
