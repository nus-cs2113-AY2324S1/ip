package duke;

public class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String description) {
        super(description);
        this.by = this.extractDeadline(description);
    }


    private String extractDeadline(String var1) {
        int var2 = var1.indexOf("/by");
        return var2 != -1 ? var1.substring(var2 + 3).trim() : "No Deadline";
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        String var1 = super.toString();
        return "[D]" + var1 + " (by: " + this.by + ")";
    }

}