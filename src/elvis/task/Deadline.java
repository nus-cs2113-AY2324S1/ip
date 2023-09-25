package elvis.task;

import elvis.operation.DateTimeHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final char taskType = 'D';
    private final int DATE = 0;
    private final int TIME = 1;
    private LocalDateTime dateTime;

    public Deadline(String description, int isDoneFromFile, String byWhen) {
        super(description, isDoneFromFile);
        this.dateTime = DateTimeHandler.dateTimeParser(byWhen);
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
