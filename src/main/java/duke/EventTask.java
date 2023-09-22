package duke;

class EventTask extends Task {
    private final String from;
    private final String to;

    public EventTask(String description) {
        super(description);
        this.from = this.extractFromDate(description);
        this.to = this.extractToDate(description);
    }

    private String extractFromDate(String description) {
        int fromIndex = description.indexOf("/from");
        if (fromIndex != -1) {
            int byIndex = description.indexOf("/to");
            if (byIndex != -1 && byIndex > fromIndex) {
                return description.substring(fromIndex + 5, byIndex).trim();
            }
        }

        return "No Start Date";
    }

    private String extractToDate(String description) {
        int toIndex = description.indexOf("/to");
        return toIndex != -1 ? description.substring(toIndex + 3).trim() : "No End Date";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toString() {
        String var10000 = super.toString();
        return "[E]" + var10000 + " (from: " + this.from + " to: " + this.to + ")";
    }
}

