package listWhisper.task;

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.deadline);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by:" + by + ")";
    }

    @Override
    public String formatAsInput() {
        return super.formatAsInput() + "/by " + this.by;
    }
}
