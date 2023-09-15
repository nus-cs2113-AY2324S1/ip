class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String description) {
        super(description);
        this.by = extractDeadline(description);
    }

    private String extractDeadline(String description) {

        // Find the index of "/by" in the cleaned description
        int byIndex = description.indexOf("/by");

        // Check if "by" is found and extract the deadline part
        if (byIndex != -1) {
            return description.substring(byIndex + 3).trim();
        } else {
            return "No Deadline"; // Return a default value if "by" is not found
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
