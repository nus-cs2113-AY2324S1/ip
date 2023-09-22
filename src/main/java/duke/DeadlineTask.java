package duke;

class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String var1) {
        super(var1);
        this.by = this.extractDeadline(var1);
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