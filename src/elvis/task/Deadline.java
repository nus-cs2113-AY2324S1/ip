package elvis.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final char taskType = 'D';
    private final int DATE = 0;
    private final int TIME = 1;
    private LocalDateTime date;

    public Deadline(String description, int isDoneFromFile, String byWhen) {
        super(description, isDoneFromFile);
        this.date = LocalDateTime.parse(byWhen);
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public LocalDateTime getDateTime() {
        return date;
    }
}
