package notGPT.task;

import java.time.LocalDateTime;
import notGPT.parser.Parser;

public class Deadlines extends Task {
    private LocalDateTime deadline;
    private Parser parser = new Parser();

    public Deadlines(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parser.formatDateTime(deadline) + ")";
    }
    
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + parser.formatDateTime(deadline);
    }

    @Override
    public String getTaskTiming() {
        return parser.formatDateTime(deadline);
    }
}
