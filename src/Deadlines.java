public class Deadlines extends Task {
    private char taskType = 'D';
    private String date;
    public Deadlines(String description, String date) {
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
