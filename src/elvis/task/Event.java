package elvis.task;

import elvis.operation.DateTimeHandler;

import java.time.LocalDateTime;

public class Event extends Task {
    private final char taskType = 'E';
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, int isDoneFromFile, String fromWhen, String toWhen) {
        super(description, isDoneFromFile);
        this.startDateTime = DateTimeHandler.dateTimeParser(fromWhen);
        this.endDateTime = DateTimeHandler.dateTimeParser(toWhen);
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
