package elvis.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final char taskType = 'E';
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, int isDoneFromFile, String startDateTime, String endDateTime) {
        super(description, isDoneFromFile);
        this.startDateTime = LocalDateTime.parse(startDateTime);
        this.endDateTime = LocalDateTime.parse(endDateTime);
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    @Override
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
