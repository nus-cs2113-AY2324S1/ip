public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public void setDeadline(String deadline) {

        this.deadline = deadline;
    }
    public String getDeadline() {
        return deadline;
    }
    @Override
    public String toString() {
        String summary = String.format("[D] %s (by: %s)", super.toString(), deadline);
        return summary;
    }
}
