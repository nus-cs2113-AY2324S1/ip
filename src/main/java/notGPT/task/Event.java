package notGPT.task;

import java.time.LocalDateTime;
import notGPT.parser.Parser;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Parser parser = new Parser();

    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + parser.formatDateTime(startTime) + " to: "
                + parser.formatDateTime(endTime) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + parser.formatDateTime(startTime) + "|"
                + parser.formatDateTime(endTime);
    }

    @Override
    public String getTaskTiming() {
        return parser.formatDateTime(startTime) + " " + parser.formatDateTime(endTime);
    }
}
