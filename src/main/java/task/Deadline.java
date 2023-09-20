package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }
    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getExcess() {
        return by;
    }
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + by + ")";
    }


}
