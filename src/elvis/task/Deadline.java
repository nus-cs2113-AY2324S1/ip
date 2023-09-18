package elvis.task;

public class Deadline extends Task {
    private final char taskType = 'D';
    private String date;

    public Deadline(String description, int isDoneFromFile, String date) {
        super(description, isDoneFromFile);
        this.date = date;
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public String getDate() {
        return date;
    }
}
