class EventTask extends Task {
    private final String from;
    private final String to;

    public EventTask(String description) {
        super(description);
        this.from = extractFromDate(description);
        this.to = extractToDate(description);
    }

    // Extract the /from date
    private String extractFromDate(String description) {
        int fromIndex = description.indexOf("/from");

        if (fromIndex != -1) {
            int byIndex = description.indexOf("/to");
            if (byIndex != -1 && byIndex > fromIndex) {
                return description.substring(fromIndex + 5, byIndex).trim(); // Extract the start date between "/from" and "/by" and trim any leading/trailing spaces
            }
        }
        return "No Start Date";
    }

    //Extract the /to date
    private String extractToDate(String description) {
        int toIndex = description.indexOf("/to");

        if (toIndex != -1) {
            return description.substring(toIndex + 3).trim();
        } else {
            return "No End Date";
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}