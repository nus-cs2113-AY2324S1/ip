package duke.task;

public class Deadlines extends Task{
    private String dueDate;

    public Deadlines(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }


    public void UpdateDueDate(String newDueDate) {
        this.dueDate = newDueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
