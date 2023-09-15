package duke;


class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String description) {
        super(description);
        this.by = this.extractDeadline(description);
    }

    private String extractDeadline(String description) {
        int byIndex = description.indexOf("/by");
        return byIndex != -1 ? description.substring(byIndex + 3).trim() : "No Deadline";
    }

    public String toString() {
        String var10000 = super.toString();
        return "[D]" + var10000 + " (by: " + this.by + ")";
    }
}
