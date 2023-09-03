public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public void printTask() {
        System.out.println("\t[D]" + getCompletedString() + getTaskName() + " (by: " + getDeadline() + ")");
    }
}
