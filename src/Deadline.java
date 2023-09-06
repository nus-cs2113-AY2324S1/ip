public class Deadline extends Task {
    private final char taskType = 'D';
    private String date;

    public Deadline(String description, String date) {
        super(description);
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
