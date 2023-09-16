package notGPT.task;

public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
    
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + deadline;
    }
}
