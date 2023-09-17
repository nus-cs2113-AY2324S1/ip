package chattie.tasks;

public class Deadline extends Task{

    private String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
